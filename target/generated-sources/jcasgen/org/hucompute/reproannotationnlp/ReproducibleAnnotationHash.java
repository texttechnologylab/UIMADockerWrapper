

/* First created by JCasGen Sat Nov 27 19:07:12 CET 2021 */
package org.hucompute.reproannotationnlp;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** An docker and engine anntation for an annotator
 * Updated by JCasGen Sat Nov 27 19:07:12 CET 2021
 * XML source: /home/alexander/Documents/ReproducibleUIMAAnnotations/target/jcasgen/typesystem.xml
 * @generated */
public class ReproducibleAnnotationHash extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ReproducibleAnnotationHash.class);
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
  protected ReproducibleAnnotationHash() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public ReproducibleAnnotationHash(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public ReproducibleAnnotationHash(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public ReproducibleAnnotationHash(JCas jcas, int begin, int end) {
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
  //* Feature: configuration_crc32

  /** getter for configuration_crc32 - gets The crc32 of the jcas
   * @generated
   * @return value of the feature 
   */
  public long getConfiguration_crc32() {
    if (ReproducibleAnnotationHash_Type.featOkTst && ((ReproducibleAnnotationHash_Type)jcasType).casFeat_configuration_crc32 == null)
      jcasType.jcas.throwFeatMissing("configuration_crc32", "org.hucompute.reproannotationnlp.ReproducibleAnnotationHash");
    return jcasType.ll_cas.ll_getLongValue(addr, ((ReproducibleAnnotationHash_Type)jcasType).casFeatCode_configuration_crc32);}
    
  /** setter for configuration_crc32 - sets The crc32 of the jcas 
   * @generated
   * @param v value to set into the feature 
   */
  public void setConfiguration_crc32(long v) {
    if (ReproducibleAnnotationHash_Type.featOkTst && ((ReproducibleAnnotationHash_Type)jcasType).casFeat_configuration_crc32 == null)
      jcasType.jcas.throwFeatMissing("configuration_crc32", "org.hucompute.reproannotationnlp.ReproducibleAnnotationHash");
    jcasType.ll_cas.ll_setLongValue(addr, ((ReproducibleAnnotationHash_Type)jcasType).casFeatCode_configuration_crc32, v);}    
  }

    