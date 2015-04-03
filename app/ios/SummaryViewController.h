//
//  TummaryViewController.h
//  Carat
//
//  Created by Muhammad Haris on 21/02/15.
//  Copyright (c) 2015 UC Berkeley. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "XYPieChart/XYPieChart.h"
#import "CorePlot-CocoaTouch.h"
#import "CorePlot/CPTAnimation.h"

@interface SummaryViewController : UIViewController<CPTPlotDataSource, CPTLegendDelegate,CPTPieChartDelegate>

@end
