/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package edu.berkeley.cs.amplab.carat.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2014-12-7")
public class TrafficRecord implements org.apache.thrift.TBase<TrafficRecord, TrafficRecord._Fields>, java.io.Serializable, Cloneable, Comparable<TrafficRecord> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TrafficRecord");

  private static final org.apache.thrift.protocol.TField RX_FIELD_DESC = new org.apache.thrift.protocol.TField("rx", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TX_FIELD_DESC = new org.apache.thrift.protocol.TField("tx", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TAG_FIELD_DESC = new org.apache.thrift.protocol.TField("tag", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TrafficRecordStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TrafficRecordTupleSchemeFactory());
  }

  public long rx; // optional
  public long tx; // optional
  public String tag; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RX((short)1, "rx"),
    TX((short)2, "tx"),
    TAG((short)3, "tag");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // RX
          return RX;
        case 2: // TX
          return TX;
        case 3: // TAG
          return TAG;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RX_ISSET_ID = 0;
  private static final int __TX_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.RX,_Fields.TX,_Fields.TAG};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RX, new org.apache.thrift.meta_data.FieldMetaData("rx", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TX, new org.apache.thrift.meta_data.FieldMetaData("tx", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TAG, new org.apache.thrift.meta_data.FieldMetaData("tag", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TrafficRecord.class, metaDataMap);
  }

  public TrafficRecord() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TrafficRecord(TrafficRecord other) {
    __isset_bitfield = other.__isset_bitfield;
    this.rx = other.rx;
    this.tx = other.tx;
    if (other.isSetTag()) {
      this.tag = other.tag;
    }
  }

  public TrafficRecord deepCopy() {
    return new TrafficRecord(this);
  }

  @Override
  public void clear() {
    setRxIsSet(false);
    this.rx = 0;
    setTxIsSet(false);
    this.tx = 0;
    this.tag = null;
  }

  public long getRx() {
    return this.rx;
  }

  public TrafficRecord setRx(long rx) {
    this.rx = rx;
    setRxIsSet(true);
    return this;
  }

  public void unsetRx() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __RX_ISSET_ID);
  }

  /** Returns true if field rx is set (has been assigned a value) and false otherwise */
  public boolean isSetRx() {
    return EncodingUtils.testBit(__isset_bitfield, __RX_ISSET_ID);
  }

  public void setRxIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __RX_ISSET_ID, value);
  }

  public long getTx() {
    return this.tx;
  }

  public TrafficRecord setTx(long tx) {
    this.tx = tx;
    setTxIsSet(true);
    return this;
  }

  public void unsetTx() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TX_ISSET_ID);
  }

  /** Returns true if field tx is set (has been assigned a value) and false otherwise */
  public boolean isSetTx() {
    return EncodingUtils.testBit(__isset_bitfield, __TX_ISSET_ID);
  }

  public void setTxIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TX_ISSET_ID, value);
  }

  public String getTag() {
    return this.tag;
  }

  public TrafficRecord setTag(String tag) {
    this.tag = tag;
    return this;
  }

  public void unsetTag() {
    this.tag = null;
  }

  /** Returns true if field tag is set (has been assigned a value) and false otherwise */
  public boolean isSetTag() {
    return this.tag != null;
  }

  public void setTagIsSet(boolean value) {
    if (!value) {
      this.tag = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case RX:
      if (value == null) {
        unsetRx();
      } else {
        setRx((Long)value);
      }
      break;

    case TX:
      if (value == null) {
        unsetTx();
      } else {
        setTx((Long)value);
      }
      break;

    case TAG:
      if (value == null) {
        unsetTag();
      } else {
        setTag((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case RX:
      return Long.valueOf(getRx());

    case TX:
      return Long.valueOf(getTx());

    case TAG:
      return getTag();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case RX:
      return isSetRx();
    case TX:
      return isSetTx();
    case TAG:
      return isSetTag();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TrafficRecord)
      return this.equals((TrafficRecord)that);
    return false;
  }

  public boolean equals(TrafficRecord that) {
    if (that == null)
      return false;

    boolean this_present_rx = true && this.isSetRx();
    boolean that_present_rx = true && that.isSetRx();
    if (this_present_rx || that_present_rx) {
      if (!(this_present_rx && that_present_rx))
        return false;
      if (this.rx != that.rx)
        return false;
    }

    boolean this_present_tx = true && this.isSetTx();
    boolean that_present_tx = true && that.isSetTx();
    if (this_present_tx || that_present_tx) {
      if (!(this_present_tx && that_present_tx))
        return false;
      if (this.tx != that.tx)
        return false;
    }

    boolean this_present_tag = true && this.isSetTag();
    boolean that_present_tag = true && that.isSetTag();
    if (this_present_tag || that_present_tag) {
      if (!(this_present_tag && that_present_tag))
        return false;
      if (!this.tag.equals(that.tag))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_rx = true && (isSetRx());
    list.add(present_rx);
    if (present_rx)
      list.add(rx);

    boolean present_tx = true && (isSetTx());
    list.add(present_tx);
    if (present_tx)
      list.add(tx);

    boolean present_tag = true && (isSetTag());
    list.add(present_tag);
    if (present_tag)
      list.add(tag);

    return list.hashCode();
  }

  @Override
  public int compareTo(TrafficRecord other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetRx()).compareTo(other.isSetRx());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRx()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rx, other.rx);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTx()).compareTo(other.isSetTx());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTx()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tx, other.tx);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTag()).compareTo(other.isSetTag());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTag()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.tag, other.tag);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TrafficRecord(");
    boolean first = true;

    if (isSetRx()) {
      sb.append("rx:");
      sb.append(this.rx);
      first = false;
    }
    if (isSetTx()) {
      if (!first) sb.append(", ");
      sb.append("tx:");
      sb.append(this.tx);
      first = false;
    }
    if (isSetTag()) {
      if (!first) sb.append(", ");
      sb.append("tag:");
      if (this.tag == null) {
        sb.append("null");
      } else {
        sb.append(this.tag);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TrafficRecordStandardSchemeFactory implements SchemeFactory {
    public TrafficRecordStandardScheme getScheme() {
      return new TrafficRecordStandardScheme();
    }
  }

  private static class TrafficRecordStandardScheme extends StandardScheme<TrafficRecord> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TrafficRecord struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RX
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.rx = iprot.readI64();
              struct.setRxIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TX
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.tx = iprot.readI64();
              struct.setTxIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TAG
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.tag = iprot.readString();
              struct.setTagIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TrafficRecord struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.isSetRx()) {
        oprot.writeFieldBegin(RX_FIELD_DESC);
        oprot.writeI64(struct.rx);
        oprot.writeFieldEnd();
      }
      if (struct.isSetTx()) {
        oprot.writeFieldBegin(TX_FIELD_DESC);
        oprot.writeI64(struct.tx);
        oprot.writeFieldEnd();
      }
      if (struct.tag != null) {
        if (struct.isSetTag()) {
          oprot.writeFieldBegin(TAG_FIELD_DESC);
          oprot.writeString(struct.tag);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TrafficRecordTupleSchemeFactory implements SchemeFactory {
    public TrafficRecordTupleScheme getScheme() {
      return new TrafficRecordTupleScheme();
    }
  }

  private static class TrafficRecordTupleScheme extends TupleScheme<TrafficRecord> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TrafficRecord struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetRx()) {
        optionals.set(0);
      }
      if (struct.isSetTx()) {
        optionals.set(1);
      }
      if (struct.isSetTag()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetRx()) {
        oprot.writeI64(struct.rx);
      }
      if (struct.isSetTx()) {
        oprot.writeI64(struct.tx);
      }
      if (struct.isSetTag()) {
        oprot.writeString(struct.tag);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TrafficRecord struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.rx = iprot.readI64();
        struct.setRxIsSet(true);
      }
      if (incoming.get(1)) {
        struct.tx = iprot.readI64();
        struct.setTxIsSet(true);
      }
      if (incoming.get(2)) {
        struct.tag = iprot.readString();
        struct.setTagIsSet(true);
      }
    }
  }

}

