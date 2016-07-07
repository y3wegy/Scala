package com.ssc.cm
package cmw2518

/**
 * *
 * modify Pledge Accepted Sent margin call.
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Accept both line item.
 * Action: Agree margin call.
 * Action: Send line item.
 * Action: Modify line item at In tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Send line item.
 * Action: Modify line item at Out tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
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
    acceptLineItem(Agree, In, 1) :::
    acceptLineItem(Agree, Out, 1) :::
    agreeMarginCall :::
    sendLineItem(Allocate) :::
    modifyLineItem(Allocate, In) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    sendLineItem(Allocate) :::
    modifyLineItem(Agree, Out) :::
    checkLineItemStaus(Agree, Out, "Pending Accepted") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}