
/* First created by JCasGen Mon Nov 22 23:22:24 CET 2021 */
package org.hucompute.reproannotationnlp;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** An docker and engine anntation for an annotator
 * Updated by JCasGen Mon Nov 22 23:22:24 CET 2021
 * @generated */
public class ReproducibleAnnotationHash_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (ReproducibleAnnotationHash_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = ReproducibleAnnotationHash_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new ReproducibleAnnotationHash(addr, ReproducibleAnnotationHash_Type.this);
  			   ReproducibleAnnotationHash_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new ReproducibleAnnotationHash(addr, ReproducibleAnnotationHash_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = ReproducibleAnnotationHash.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.hucompute.reproannotationnlp.ReproducibleAnnotationHash");
 
  /** @generated */
  final Feature casFeat_configuration_crc32;
  /** @generated */
  final int     casFeatCode_configuration_crc32;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public long getConfiguration_crc32(int addr) {
        if (featOkTst && casFeat_configuration_crc32 == null)
      jcas.throwFeatMissing("configuration_crc32", "org.hucompute.reproannotationnlp.ReproducibleAnnotationHash");
    return ll_cas.ll_getLongValue(addr, casFeatCode_configuration_crc32);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setConfiguration_crc32(int addr, long v) {
        if (featOkTst && casFeat_configuration_crc32 == null)
      jcas.throwFeatMissing("configuration_crc32", "org.hucompute.reproannotationnlp.ReproducibleAnnotationHash");
    ll_cas.ll_setLongValue(addr, casFeatCode_configuration_crc32, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public ReproducibleAnnotationHash_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_configuration_crc32 = jcas.getRequiredFeatureDE(casType, "configuration_crc32", "uima.cas.Long", featOkTst);
    casFeatCode_configuration_crc32  = (null == casFeat_configuration_crc32) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_configuration_crc32).getCode();

  }
}



    