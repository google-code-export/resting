/**
 * This package has the transformation logic for converting the HTTP response into value objects. The {@link com.google.resting.transform.Transformer} 
 * interface can be implemented to write customized transformation logic. The known implementations are {@link com.google.resting.transform.impl.JSONTransformer} 
 * and {@link com.google.resting.transform.impl.XMLTransformer}
 */
package com.google.resting.transform;