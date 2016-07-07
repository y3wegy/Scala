package com.ssc.cm
package cmw2517

/**
 * *
 * no line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Issue created margin call if it is Client Call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Agree margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Send margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 *
 */
object Case1 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    agreeMarginCall :::
    checkLineItemGroupStatus("Pending Pledge") :::
    sendLineItem(Allocate) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}