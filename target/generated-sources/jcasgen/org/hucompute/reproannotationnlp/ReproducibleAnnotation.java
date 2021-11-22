

/* First created by JCasGen Mon Nov 22 23:48:21 CET 2021 */
package org.hucompute.reproannotationnlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** An docker and engine anntation for an annotator
 * Updated by JCasGen Mon Nov 22 23:48:21 CET 2021
 * XML source: /home/alexander/Documents/ReproducibleUIMAAnnotations/target/jcasgen/typesystem.xml
 * @generated */
public class ReproducibleAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ReproducibleAnnotation.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected ReproducibleAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ReproducibleAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ReproducibleAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ReproducibleAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: configuration_hash

  /** getter for configuration_hash - gets The hash of the configuration setup
   * @generated
   * @return value of the feature 
   */
  public long getConfiguration_hash() {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_configuration_hash == null)
      jcasType.jcas.throwFeatMissing("configuration_hash", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return jcasType.ll_cas.ll_getLongValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_configuration_hash);}
    
  /** setter for configuration_hash - sets The hash of the configuration setup 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfiguration_hash(long v) {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_configuration_hash == null)
      jcasType.jcas.throwFeatMissing("configuration_hash", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    jcasType.ll_cas.ll_setLongValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_configuration_hash, v);}    
   
    
  //*--------------*
  //* Feature: compression

  /** getter for compression - gets 
   * @generated
   * @return value of the feature 
   */
  public String getCompression() {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_compression == null)
      jcasType.jcas.throwFeatMissing("compression", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_compression);}
    
  /** setter for compression - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setCompression(String v) {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_compression == null)
      jcasType.jcas.throwFeatMissing("compression", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_compression, v);}    
   
    
  //*--------------*
  //* Feature: configuration

  /** getter for configuration - gets The configuration for the annotation, containing the dockerfile etc.
   * @generated
   * @return value of the feature 
   */
  public String getConfiguration() {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_configuration == null)
      jcasType.jcas.throwFeatMissing("configuration", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_configuration);}
    
  /** setter for configuration - sets The configuration for the annotation, containing the dockerfile etc. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfiguration(String v) {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_configuration == null)
      jcasType.jcas.throwFeatMissing("configuration", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_configuration, v);}    
   
    
  //*--------------*
  //* Feature: timestamp

  /** getter for timestamp - gets The timestamp of the annotation given in ms since epoch.
   * @generated
   * @return value of the feature 
   */
  public long getTimestamp() {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return jcasType.ll_cas.ll_getLongValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_timestamp);}
    
  /** setter for timestamp - sets The timestamp of the annotation given in ms since epoch. 
   * @generated
   * @param v value to set into the feature 
   */
  public void setTimestamp(long v) {
    if (ReproducibleAnnotation_Type.featOkTst && ((ReproducibleAnnotation_Type)jcasType).casFeat_timestamp == null)
      jcasType.jcas.throwFeatMissing("timestamp", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    jcasType.ll_cas.ll_setLongValue(addr, ((ReproducibleAnnotation_Type)jcasType).casFeatCode_timestamp, v);}    
  }

    