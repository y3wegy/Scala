package com.ssc.cm
package cmw2517

/**
 * *
 * send accepted line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Agree margin call.
 * Action: Accept line item at In tab.
 * Action: Send line item.
 * Check:  A margin call with Pledge Accepted Sent as Line Item Group Status.
 * Action: Delete line item at In tab.
 * Action: Send line item.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 *
 */
object Case5 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    agreeMarginCall :::
    acceptLineItem(Allocate, In, 1) :::
    sendLineItem(Allocate) :::
    checkLineItemGroupStatus("Pledge Accepted Sent") :::
    deleteLineItem(Allocate, In, 1) :::
    sendLineItem(Allocate) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}