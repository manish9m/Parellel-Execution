/**
 * 
 */
package com.monys.parellel.execution;

import java.util.List;

/**
 * @author Manish Kumar Mishra
 *
 */
public interface ResultMerger<U> {
    public U merge(List<ServiceHolder<?>> inputResults);
}
