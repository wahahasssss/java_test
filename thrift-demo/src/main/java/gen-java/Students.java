/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2018-08-27")
public class Students implements org.apache.thrift.TBase<Students, Students._Fields>, java.io.Serializable, Cloneable, Comparable<Students> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Students");

  private static final org.apache.thrift.protocol.TField AGE_FIELD_DESC = new org.apache.thrift.protocol.TField("age", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("name", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField GENDER_FIELD_DESC = new org.apache.thrift.protocol.TField("gender", org.apache.thrift.protocol.TType.BOOL, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new StudentsStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new StudentsTupleSchemeFactory();

  public int age; // required
  public java.lang.String name; // required
  public boolean gender; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AGE((short)1, "age"),
    NAME((short)2, "name"),
    GENDER((short)3, "gender");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // AGE
          return AGE;
        case 2: // NAME
          return NAME;
        case 3: // GENDER
          return GENDER;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __AGE_ISSET_ID = 0;
  private static final int __GENDER_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AGE, new org.apache.thrift.meta_data.FieldMetaData("age", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.NAME, new org.apache.thrift.meta_data.FieldMetaData("name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.GENDER, new org.apache.thrift.meta_data.FieldMetaData("gender", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Students.class, metaDataMap);
  }

  public Students() {
  }

  public Students(
    int age,
    java.lang.String name,
    boolean gender)
  {
    this();
    this.age = age;
    setAgeIsSet(true);
    this.name = name;
    this.gender = gender;
    setGenderIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Students(Students other) {
    __isset_bitfield = other.__isset_bitfield;
    this.age = other.age;
    if (other.isSetName()) {
      this.name = other.name;
    }
    this.gender = other.gender;
  }

  public Students deepCopy() {
    return new Students(this);
  }

  @Override
  public void clear() {
    setAgeIsSet(false);
    this.age = 0;
    this.name = null;
    setGenderIsSet(false);
    this.gender = false;
  }

  public int getAge() {
    return this.age;
  }

  public Students setAge(int age) {
    this.age = age;
    setAgeIsSet(true);
    return this;
  }

  public void unsetAge() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AGE_ISSET_ID);
  }

  /** Returns true if field age is set (has been assigned a value) and false otherwise */
  public boolean isSetAge() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AGE_ISSET_ID);
  }

  public void setAgeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AGE_ISSET_ID, value);
  }

  public java.lang.String getName() {
    return this.name;
  }

  public Students setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  public void unsetName() {
    this.name = null;
  }

  /** Returns true if field name is set (has been assigned a value) and false otherwise */
  public boolean isSetName() {
    return this.name != null;
  }

  public void setNameIsSet(boolean value) {
    if (!value) {
      this.name = null;
    }
  }

  public boolean isGender() {
    return this.gender;
  }

  public Students setGender(boolean gender) {
    this.gender = gender;
    setGenderIsSet(true);
    return this;
  }

  public void unsetGender() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __GENDER_ISSET_ID);
  }

  /** Returns true if field gender is set (has been assigned a value) and false otherwise */
  public boolean isSetGender() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __GENDER_ISSET_ID);
  }

  public void setGenderIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __GENDER_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case AGE:
      if (value == null) {
        unsetAge();
      } else {
        setAge((java.lang.Integer)value);
      }
      break;

    case NAME:
      if (value == null) {
        unsetName();
      } else {
        setName((java.lang.String)value);
      }
      break;

    case GENDER:
      if (value == null) {
        unsetGender();
      } else {
        setGender((java.lang.Boolean)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case AGE:
      return getAge();

    case NAME:
      return getName();

    case GENDER:
      return isGender();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case AGE:
      return isSetAge();
    case NAME:
      return isSetName();
    case GENDER:
      return isSetGender();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof Students)
      return this.equals((Students)that);
    return false;
  }

  public boolean equals(Students that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_age = true;
    boolean that_present_age = true;
    if (this_present_age || that_present_age) {
      if (!(this_present_age && that_present_age))
        return false;
      if (this.age != that.age)
        return false;
    }

    boolean this_present_name = true && this.isSetName();
    boolean that_present_name = true && that.isSetName();
    if (this_present_name || that_present_name) {
      if (!(this_present_name && that_present_name))
        return false;
      if (!this.name.equals(that.name))
        return false;
    }

    boolean this_present_gender = true;
    boolean that_present_gender = true;
    if (this_present_gender || that_present_gender) {
      if (!(this_present_gender && that_present_gender))
        return false;
      if (this.gender != that.gender)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + age;

    hashCode = hashCode * 8191 + ((isSetName()) ? 131071 : 524287);
    if (isSetName())
      hashCode = hashCode * 8191 + name.hashCode();

    hashCode = hashCode * 8191 + ((gender) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(Students other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAge()).compareTo(other.isSetAge());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAge()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.age, other.age);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetName()).compareTo(other.isSetName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.name, other.name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetGender()).compareTo(other.isSetGender());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGender()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gender, other.gender);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("Students(");
    boolean first = true;

    sb.append("age:");
    sb.append(this.age);
    first = false;
    if (!first) sb.append(", ");
    sb.append("name:");
    if (this.name == null) {
      sb.append("null");
    } else {
      sb.append(this.name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("gender:");
    sb.append(this.gender);
    first = false;
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class StudentsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public StudentsStandardScheme getScheme() {
      return new StudentsStandardScheme();
    }
  }

  private static class StudentsStandardScheme extends org.apache.thrift.scheme.StandardScheme<Students> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Students struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AGE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.age = iprot.readI32();
              struct.setAgeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.name = iprot.readString();
              struct.setNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // GENDER
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.gender = iprot.readBool();
              struct.setGenderIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, Students struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(AGE_FIELD_DESC);
      oprot.writeI32(struct.age);
      oprot.writeFieldEnd();
      if (struct.name != null) {
        oprot.writeFieldBegin(NAME_FIELD_DESC);
        oprot.writeString(struct.name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(GENDER_FIELD_DESC);
      oprot.writeBool(struct.gender);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class StudentsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public StudentsTupleScheme getScheme() {
      return new StudentsTupleScheme();
    }
  }

  private static class StudentsTupleScheme extends org.apache.thrift.scheme.TupleScheme<Students> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Students struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAge()) {
        optionals.set(0);
      }
      if (struct.isSetName()) {
        optionals.set(1);
      }
      if (struct.isSetGender()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetAge()) {
        oprot.writeI32(struct.age);
      }
      if (struct.isSetName()) {
        oprot.writeString(struct.name);
      }
      if (struct.isSetGender()) {
        oprot.writeBool(struct.gender);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Students struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.age = iprot.readI32();
        struct.setAgeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.name = iprot.readString();
        struct.setNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.gender = iprot.readBool();
        struct.setGenderIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

