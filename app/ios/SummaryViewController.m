//
//  SBCViewController.m
//  Carat
//
//  Created by Muhammad Haris on 20/02/15.
//  Copyright (c) 2015 UC Berkeley. All rights reserved.
//

#import "SummaryViewController.h"
#import "CoreDataManager.h"
#import "CaratProtocol.h"
#import "Utilities.h"
#import "CaratConstants.h"

#define DEGREES_RADIANS(angle) ((angle) / 180.0 * M_PI)
#define kSTATS_URL @"http://carat.cs.helsinki.fi/statistics-data/stats.json"
NSString* const kKey = @"key";
NSString* const kValue = @"value";
NSString* const kWellBehaved = @"well-behaved";
NSString* const kHogs = @"hogs";
NSString* const kBugs = @"bugs";


typedef NS_ENUM(NSUInteger, PieChartSlices) {
	kBugsSlice = 0,
	kHogsSlice = 1,
	kWellbehavedSlice = 2
};

@interface PchartSlice : NSObject
@property (assign, nonatomic) NSUInteger sliceId;
@property (strong, nonatomic) UIColor* color;
@property (strong, nonatomic) NSString* name;
@property (assign, nonatomic) double percentValue;
@end

@implementation PchartSlice


@end

@interface SummaryViewController ()

@property (strong, nonatomic) NSMutableArray* slices;
@property (nonatomic, retain) IBOutlet XYPieChart* summaryChart;
@property(nonatomic, retain) IBOutlet UIView* piechartContainer;
@property (nonatomic, retain) CPTGraphHostingView *hostView;
@property (nonatomic, retain) CPTTheme *selectedTheme;
@property (nonatomic, retain) CPTPieChart *pieChart;
@property (nonatomic, retain) CPTGraph *graph;
@property (nonatomic, retain) CPTLegend *theLegend;
@property (nonatomic, retain) HogBugReport* hogReport;
@property (nonatomic, retain) HogBugReport* bugReport;
@property (nonatomic, retain) IBOutlet UILabel* hogs;
@property (nonatomic, retain) IBOutlet UILabel* bugs;
@property (nonatomic, retain) IBOutlet UILabel* battery;
@property (nonatomic, retain) IBOutlet UIView *topView;
@end

@implementation SummaryViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
	self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
	if (self) {
		self.title = @"Summary";
		self.tabBarItem.image = [UIImage imageNamed:@"settings"];
	}
	return self;
}

-(void) viewDidAppear:(BOOL)animated{
	[super viewDidAppear:animated];
}

-(void)initPlot {
	[self configureHost];
	[self configureGraph];
	[self configureChart];
	[self configureLegend];
}

-(void)configureHost {
	// 1 - Set up view frame
	CGRect parentRect = self.piechartContainer.frame;
	self.hostView = [[(CPTGraphHostingView *) [CPTGraphHostingView alloc] initWithFrame:parentRect] autorelease];
	self.hostView.allowPinchScaling = NO;

	[self.view addSubview:self.hostView];
}

-(void)configureGraph {
	// 1 - Create and initialise graph
	CPTGraph *graph = [[[CPTXYGraph alloc] initWithFrame:self.hostView.bounds] autorelease];
	self.hostView.hostedGraph = graph;
	graph.paddingLeft = 0.0f;
	graph.paddingTop = 0.0f;
	graph.paddingRight = 0.0f;
	graph.paddingBottom = 0.0f;
	graph.axisSet = nil;
	// 2 - Set up text style
//	CPTMutableTextStyle *textStyle = [CPTMutableTextStyle textStyle];
//	textStyle.color = [CPTColor grayColor];
//	textStyle.fontName = @"Helvetica-Bold";
//	textStyle.fontSize = 16.0f;
	// 4 - Set theme

	self.selectedTheme = [CPTTheme themeNamed:kCPTPlainWhiteTheme];
	[graph applyTheme:self.selectedTheme];

}

-(void)configureChart {
	// 1 - Get reference to graph
	self.hostView.autoresizingMask= UIViewAutoresizingFlexibleWidth|UIViewAutoresizingFlexibleLeftMargin|UIViewAutoresizingFlexibleRightMargin|UIViewAutoresizingFlexibleTopMargin|UIViewAutoresizingFlexibleHeight|UIViewAutoresizingFlexibleBottomMargin;
	CPTGraph *graph = self.hostView.hostedGraph;
	// 2 - Create chart
	self.pieChart = [[[CPTPieChart alloc] init] autorelease];
	self.pieChart.dataSource = self;
	self.pieChart.delegate = self;
	self.pieChart.pieRadius = (self.hostView.bounds.size.height * 0.85) / 2;
	self.pieChart.identifier = graph.title;
	self.pieChart.startAngle = M_PI_4;
	self.pieChart.sliceDirection = CPTPieDirectionClockwise;
	// 3 - Create gradient
	CPTGradient *overlayGradient = [[[CPTGradient alloc] init] autorelease];
	overlayGradient.gradientType = CPTGradientTypeRadial;
	overlayGradient = [overlayGradient addColorStop:[[CPTColor blackColor] colorWithAlphaComponent:0.0] atPosition:0.9];
	overlayGradient = [overlayGradient addColorStop:[[CPTColor blackColor] colorWithAlphaComponent:0.4] atPosition:1.0];
	self.pieChart.overlayFill = [CPTFill fillWithGradient:overlayGradient];
	self.pieChart.centerAnchor = CGPointMake(0.5, 0.45);
	//pieChart.pieInnerRadius = pieChart.pieRadius/2;


	CPTMutableLineStyle *myLineStyle = [CPTMutableLineStyle lineStyle];
	myLineStyle.lineColor = [CPTColor whiteColor];
	myLineStyle.lineWidth = 1.0;
	self.pieChart.borderLineStyle = myLineStyle;

	CABasicAnimation* fadeAnim = [CABasicAnimation animationWithKeyPath:@"opacity"];
	fadeAnim.fromValue = [NSNumber numberWithFloat:0.0];
	fadeAnim.toValue = [NSNumber numberWithFloat:1.0];
	fadeAnim.duration = 1.0;
	[self.pieChart addAnimation:fadeAnim forKey:@"opacity"];

	// 4 - Add chart to graph
	[graph addPlot:self.pieChart];
	[graph.defaultPlotSpace scaleToFitPlots:[graph allPlots]];

}

-(void)configureLegend {
	// 1 - Get graph instance
	CPTGraph *graph = self.hostView.hostedGraph;
	// 2 - Create legend
	self.theLegend = [CPTLegend legendWithGraph:graph];
	// 3 - Configure legen
	self.theLegend.numberOfRows = 1;
	self.theLegend.fill = [CPTFill fillWithColor:[CPTColor whiteColor]];
	self.theLegend.borderLineStyle = [CPTLineStyle lineStyle];
	self.theLegend.cornerRadius = 5.0;
	// 4 - Add legend to graph
	graph.legend = self.theLegend;
	graph.legendAnchor = CPTRectAnchorTop;
	CPTMutableTextStyle *mySmallerTextStyle = [[[CPTMutableTextStyle alloc] init] autorelease];
//
//	//This is the important property for your needs
	[mySmallerTextStyle setFontSize:self.hostView.bounds.size.height * 0.05];
	CGFloat legendPadding = -(5.0);
	//[[graph legend] setTextStyle:(CPTTextStyle *)mySmallerTextStyle];
	graph.legendDisplacement = CGPointMake(0.0, legendPadding);

}

#pragma mark - CPTPlotDataSource methods
-(NSUInteger)numberOfRecordsForPlot:(CPTPlot *)plot {
	return self.slices.count;
}

-(NSNumber *)numberForPlot:(CPTPlot *)plot field:(NSUInteger)fieldEnum recordIndex:(NSUInteger)index {

	PchartSlice* slice = [self.slices objectAtIndex:index];
	return [NSDecimalNumber numberWithInteger:slice.percentValue];
}

-(CPTLayer *)dataLabelForPlot:(CPTPlot *)plot recordIndex:(NSUInteger)index {
	// 1 - Define label text style
	static CPTMutableTextStyle *labelText = nil;
	if (!labelText) {
		labelText= [[CPTMutableTextStyle alloc] init];
		labelText.color = [CPTColor grayColor];
	}

	if (self.interfaceOrientation == UIInterfaceOrientationLandscapeLeft||
		self.interfaceOrientation == UIInterfaceOrientationLandscapeRight) {
		labelText.fontSize = self.hostView.bounds.size.width * 0.025;
	}
	else if (self.interfaceOrientation == UIInterfaceOrientationPortrait && UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPhone)
		labelText.fontSize = self.hostView.bounds.size.width * 0.0395;
	else
		labelText.fontSize = self.hostView.bounds.size.width * 0.04;

	// 2 - Calculate portfolio total value
	NSNumber *portfolioSum = [NSDecimalNumber zero];
	PchartSlice* slice = [self.slices objectAtIndex:index];
	portfolioSum = [NSDecimalNumber numberWithDouble:slice.percentValue];
	// 3 - Calculate percentage value
	// 4 - Set up display label
	NSString *labelValue = [NSString stringWithFormat:@"%.2f%%",[portfolioSum doubleValue]];
	// 5 - Create and return layer with label text
	return [[CPTTextLayer alloc] initWithText:labelValue style:labelText];
}

-(NSString *)legendTitleForPieChart:(CPTPieChart *)pieChart recordIndex:(NSUInteger)index {
	switch (index) {
  case kBugsSlice:
			return @"Bugs";
  case kHogsSlice:
			return @"Hogs";
	}
	return @"Fine Apps";
}

-(CPTFill *)sliceFillForPieChart:(CPTPieChart *)pieChart recordIndex:(NSUInteger)index;
{
	CPTFill *color;
	switch (index) {
  case kBugsSlice:{
	  UIColor *fill = [UIColor colorWithRed:((float)252/255) green:((float)13/255) blue:((float)27/255) alpha:1.0];
			return color = [CPTFill fillWithColor:[CPTColor colorWithCGColor:fill.CGColor]];
		}

  case kHogsSlice:{
	  UIColor *fill = [UIColor colorWithRed:((float)242/255) green:((float)146/255) blue:((float)71/255) alpha:1.0];
			return color = [CPTFill fillWithColor:[CPTColor colorWithCGColor:fill.CGColor]];
		}
	}

	UIColor *fill = [UIColor colorWithRed:((float)10/255) green:((float)101/255) blue:((float)12/255) alpha:1.0];
	return color = [CPTFill fillWithColor:[CPTColor colorWithCGColor:fill.CGColor]];

}

-(void)drawSwatchForLegend:(CPTLegend *)legend atIndex:(NSUInteger)index inRect:(CGRect)rect inContext:(CGContextRef)context{
	legend.frame = CGRectMake(0, 0, 30, 30);
}

- (void)viewDidLoad {
	[super viewDidLoad];
	self.hogReport = [[CoreDataManager instance] getHogs:NO withoutHidden:YES];
	self.bugReport = [[CoreDataManager instance] getBugs:NO withoutHidden:YES];

	self.hogs.text = [NSString stringWithFormat:@"%lu Hogs",(unsigned long)self.hogReport.hbList.count];
	self.bugs.text = [NSString stringWithFormat:@"%lu Bugs",(unsigned long)self.bugReport.hbList.count];
    dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_HIGH, 0)
				   ,^{
					   NSURL* statsURL = [NSURL URLWithString:kSTATS_URL];
					   NSData* data = [NSData dataWithContentsOfURL:
									   statsURL];
					   dispatch_async(dispatch_get_main_queue(), ^(void){
						   [self _processDataFromURL:data];
						});
				   }
				  );
	// Do any additional setup after loading the view from its nib.
}

-(void) _processDataFromURL:(NSData*)data{

	NSError* error;
	NSDictionary* json = [NSJSONSerialization
						  JSONObjectWithData:data

						  options:kNilOptions
						  error:&error];

	NSArray* iosApps = json[@"ios-apps"];
	double totalValue = 0;
	double wellBehavedValue = 0;
	double bugsValue = 0;
	double hogsValue = 0;
	for (NSDictionary * dict in iosApps) {
		NSString* key = dict[kKey];
		NSNumber* number = dict[kValue];

		if([key isEqualToString:kWellBehaved]){
			wellBehavedValue = [number integerValue];
			totalValue += wellBehavedValue;
		}
		else if ([key isEqualToString:kBugs]){
			bugsValue = [number integerValue];
			totalValue += bugsValue;
		}
		else if ([key isEqualToString:kHogs]){
			hogsValue = [number integerValue];
			totalValue+= hogsValue;
		}
		else
			NSAssert(key!=nil, @"invalid key");
	}

	self.slices = [NSMutableArray arrayWithCapacity:3];

	for(int i = 0; i <= 2; i ++)
	{
		PchartSlice* slice = [[[PchartSlice alloc] init] autorelease];
		switch (i) {
			case kHogsSlice:
				slice.sliceId = kHogsSlice;
				slice.name = @"Hogs";
				slice.percentValue = (hogsValue/totalValue)*100;
				slice.color = [UIColor redColor];
				break;
			case kBugsSlice:
				slice.sliceId = kBugsSlice;
				slice.name = @"Bugs";
				slice.percentValue = (bugsValue/totalValue)*100;
				slice.color = [UIColor orangeColor];
				break;
			default:
				slice.sliceId = kWellbehavedSlice;
				slice.name = @"Well Behaved Apps";
				slice.percentValue = (wellBehavedValue/totalValue)*100;
				slice.color = [UIColor blueColor];
				break;
		}

		[self.slices addObject:slice];
	}
	[self initPlot];
}

-(void) viewWillAppear:(BOOL)animated{
	[super viewWillAppear:animated];

	// Expected Battery Life
	NSTimeInterval eb; // expected life in seconds
	double jev = [[[CoreDataManager instance] getJScoreInfo:YES] expectedValue];
	if (jev > 0) eb = MIN(MAX_BATTERY_LIFE,100/jev);
	else eb = MAX_BATTERY_LIFE;
	self.battery.text = [[Utilities doubleAsTimeNSString:eb] stringByTrimmingCharactersInSet:
				   [NSCharacterSet whitespaceAndNewlineCharacterSet]];


}

- (void)didReceiveMemoryWarning {
	[super didReceiveMemoryWarning];
	// Dispose of any resources that can be recreated.
}

-(void) dealloc{
	[super dealloc];

	self.summaryChart = nil;
	self.slices = nil;
}

-(void)viewWillLayoutSubviews{
	[super viewWillLayoutSubviews];

	self.hostView.hostedGraph.frame = self.hostView.bounds;

	self.pieChart.pieRadius = (self.hostView.bounds.size.height * 0.75) / 2;
	self.pieChart.centerAnchor = CGPointMake(0.5, 0.425);

	if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad){
		self.pieChart.pieRadius = (self.hostView.bounds.size.height * 0.85) / 2;
		self.pieChart.centerAnchor = CGPointMake(0.5, 0.45);

	}

	CPTMutableTextStyle *mySmallerTextStyle = [[[CPTMutableTextStyle alloc] init] autorelease];

	//This is the important property for your needs
	[mySmallerTextStyle setFontSize:self.hostView.bounds.size.height * 0.05];
	[self.theLegend setTextStyle:(CPTTextStyle *)mySmallerTextStyle];
	if(self.interfaceOrientation == UIInterfaceOrientationLandscapeLeft||
	   self.interfaceOrientation == UIInterfaceOrientationLandscapeRight)
	{
		CGRect rect = self.topView.frame;
		rect.origin.y = self.navigationController.navigationBar.frame.size.height+10;
		self.topView.frame = rect;
		CPTMutableTextStyle *mySmallerTextStyle = [[[CPTMutableTextStyle alloc] init] autorelease];

		[mySmallerTextStyle setFontSize:self.hostView.bounds.size.height * 0.08];
		[self.theLegend setTextStyle:(CPTTextStyle *)mySmallerTextStyle];
		self.pieChart.centerAnchor = CGPointMake(0.5, 0.365);
		self.pieChart.pieRadius = (self.hostView.bounds.size.height * 0.7) / 2;
		if(UI_USER_INTERFACE_IDIOM() == UIUserInterfaceIdiomPad){
			self.pieChart.pieRadius = (self.hostView.bounds.size.height * 0.775) / 2;
			self.pieChart.centerAnchor = CGPointMake(0.5, 0.415);

		}

	}
	[self.hostView.hostedGraph reloadData];
}

-(IBAction)linkClicked:(id)sender{
	[[UIApplication sharedApplication] openURL:[NSURL URLWithString:@"http://carat.cs.helsinki.fi/statistics/"]];
}

/*
 #pragma mark - Navigation

 // In a storyboard-based application, you will often want to do a little preparation before navigation
 - (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
 // Get the new view controller using [segue destinationViewController].
 // Pass the selected object to the new view controller.
 }
 */

@end
