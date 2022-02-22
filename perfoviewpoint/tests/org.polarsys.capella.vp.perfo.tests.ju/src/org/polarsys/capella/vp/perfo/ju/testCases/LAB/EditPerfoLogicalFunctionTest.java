/*******************************************************************************
 * Copyright (c) 2021 Obeo
 *   This program and the accompanying materials are made available under the
 *   terms of the Eclipse Public License 2.0 which is available at
 *   http://www.eclipse.org/legal/epl-2.0
 *   
 *   SPDX-License-Identifier: EPL-2.0
 * 
 *   Contributors:
 *      Obeo - initial API and implementation
 ******************************************************************************/
package org.polarsys.capella.vp.perfo.ju.testCases.LAB;

import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.vp.perfo.perfo.impl.TimeCapacityImpl;
import org.polarsys.capella.vp.perfo.perfo.impl.TimeConsumptionImpl;

/**
 * This test case checks if when editing the perfo of a LogicalFunction the perfo of
 * the FunctionalChains it is a part of are re-calculated.
 * Used to check if the listener responds to the notification SET
 */
public class EditPerfoLogicalFunctionTest extends LABPerfoTest {

	@Override
	public void test() throws Exception {
		LogicalFunction logicalFunction3 = (LogicalFunction) rootAbstractFunction.getOwnedFunctions().get(2);
		FunctionalChain functionalChain = logicalFunction3.getInvolvingFunctionalChains().get(0);
		
		editPerfoLogicalFunction(logicalFunction3, 50);
		
		assertEquals("The performance of LF3 was not changed after its modification",((TimeConsumptionImpl) logicalFunction3.getOwnedExtensions().get(0)).getValue(), 50);
		assertEquals("The performance of FC1 was not changed after the modification of LF3", ((TimeCapacityImpl) functionalChain.getOwnedExtensions().get(0)).getCurrentExecutionTime(),
				140);
	}

}
