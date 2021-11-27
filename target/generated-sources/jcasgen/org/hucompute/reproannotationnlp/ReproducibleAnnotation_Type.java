
/* First created by JCasGen Sat Nov 27 19:07:12 CET 2021 */
package org.hucompute.reproannotationnlp;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** An docker and engine anntation for an annotator
 * Updated by JCasGen Sat Nov 27 19:07:12 CET 2021
 * @generated */
public class ReproducibleAnnotation_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ReproducibleAnnotation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.hucompute.reproannotationnlp.ReproducibleAnnotation");
 
  /** @generated */
  final Feature casFeat_configuration_hash;
  /** @generated */
  final int     casFeatCode_configuration_hash;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public long getConfiguration_hash(int addr) {
        if (featOkTst && casFeat_configuration_hash == null)
      jcas.throwFeatMissing("configuration_hash", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return ll_cas.ll_getLongValue(addr, casFeatCode_configuration_hash);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConfiguration_hash(int addr, long v) {
        if (featOkTst && casFeat_configuration_hash == null)
      jcas.throwFeatMissing("configuration_hash", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    ll_cas.ll_setLongValue(addr, casFeatCode_configuration_hash, v);}
    
  
 
  /** @generated */
  final Feature casFeat_compression;
  /** @generated */
  final int     casFeatCode_compression;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCompression(int addr) {
        if (featOkTst && casFeat_compression == null)
      jcas.throwFeatMissing("compression", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_compression);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCompression(int addr, String v) {
        if (featOkTst && casFeat_compression == null)
      jcas.throwFeatMissing("compression", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_compression, v);}
    
  
 
  /** @generated */
  final Feature casFeat_configuration;
  /** @generated */
  final int     casFeatCode_configuration;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getConfiguration(int addr) {
        if (featOkTst && casFeat_configuration == null)
      jcas.throwFeatMissing("configuration", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_configuration);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConfiguration(int addr, String v) {
        if (featOkTst && casFeat_configuration == null)
      jcas.throwFeatMissing("configuration", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    ll_cas.ll_setStringValue(addr, casFeatCode_configuration, v);}
    
  
 
  /** @generated */
  final Feature casFeat_timestamp;
  /** @generated */
  final int     casFeatCode_timestamp;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public long getTimestamp(int addr) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    return ll_cas.ll_getLongValue(addr, casFeatCode_timestamp);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTimestamp(int addr, long v) {
        if (featOkTst && casFeat_timestamp == null)
      jcas.throwFeatMissing("timestamp", "org.hucompute.reproannotationnlp.ReproducibleAnnotation");
    ll_cas.ll_setLongValue(addr, casFeatCode_timestamp, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ReproducibleAnnotation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_configuration_hash = jcas.getRequiredFeatureDE(casType, "configuration_hash", "uima.cas.Long", featOkTst);
    casFeatCode_configuration_hash  = (null == casFeat_configuration_hash) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_configuration_hash).getCode();

 
    casFeat_compression = jcas.getRequiredFeatureDE(casType, "compression", "uima.cas.String", featOkTst);
    casFeatCode_compression  = (null == casFeat_compression) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_compression).getCode();

 
    casFeat_configuration = jcas.getRequiredFeatureDE(casType, "configuration", "uima.cas.String", featOkTst);
    casFeatCode_configuration  = (null == casFeat_configuration) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_configuration).getCode();

 
    casFeat_timestamp = jcas.getRequiredFeatureDE(casType, "timestamp", "uima.cas.Long", featOkTst);
    casFeatCode_timestamp  = (null == casFeat_timestamp) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_timestamp).getCode();

  }
}



    