/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.baidu.usercenter.api;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class OldPasswordException extends org.apache.avro.specific.SpecificExceptionBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"error\",\"name\":\"OldPasswordException\",\"namespace\":\"com.baidu.usercenter.api\",\"fields\":[]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  public OldPasswordException() {
    super();
  }
  
  public OldPasswordException(Object value) {
    super(value);
  }

  public OldPasswordException(Throwable cause) {
    super(cause);
  }

  public OldPasswordException(Object value, Throwable cause) {
    super(value, cause);
  }
  
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /** Creates a new OldPasswordException RecordBuilder */
  public static com.baidu.usercenter.api.OldPasswordException.Builder newBuilder() {
    return new com.baidu.usercenter.api.OldPasswordException.Builder();
  }
  
  /** Creates a new OldPasswordException RecordBuilder by copying an existing Builder */
  public static com.baidu.usercenter.api.OldPasswordException.Builder newBuilder(com.baidu.usercenter.api.OldPasswordException.Builder other) {
    return new com.baidu.usercenter.api.OldPasswordException.Builder(other);
  }
  
  /** Creates a new OldPasswordException RecordBuilder by copying an existing OldPasswordException instance */
  public static com.baidu.usercenter.api.OldPasswordException.Builder newBuilder(com.baidu.usercenter.api.OldPasswordException other) {
    return new com.baidu.usercenter.api.OldPasswordException.Builder(other);
  }
  
  /**
   * RecordBuilder for OldPasswordException instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificErrorBuilderBase<OldPasswordException>
    implements org.apache.avro.data.ErrorBuilder<OldPasswordException> {


    /** Creates a new Builder */
    private Builder() {
      super(com.baidu.usercenter.api.OldPasswordException.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.baidu.usercenter.api.OldPasswordException.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing OldPasswordException instance */
    private Builder(com.baidu.usercenter.api.OldPasswordException other) {
      super(other);
    }

    @Override
    public com.baidu.usercenter.api.OldPasswordException.Builder setValue(Object value) {
      super.setValue(value);
      return this;
    }
    
    @Override
    public com.baidu.usercenter.api.OldPasswordException.Builder clearValue() {
      super.clearValue();
      return this;
    }

    @Override
    public com.baidu.usercenter.api.OldPasswordException.Builder setCause(Throwable cause) {
      super.setCause(cause);
      return this;
    }
    
    @Override
    public com.baidu.usercenter.api.OldPasswordException.Builder clearCause() {
      super.clearCause();
      return this;
    }

    @Override
    public OldPasswordException build() {
      try {
        OldPasswordException record = new OldPasswordException(getValue(), getCause());
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
