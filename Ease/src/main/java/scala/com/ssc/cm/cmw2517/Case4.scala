package com.ssc.cm
package cmw2517

/**
 * *
 * complete unsent line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Accept line item at In tab.
 * Action: Accept line item at Out tab.
 * Action: Agree margin call.
 * Action: Complete accepted line item at Out tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Complete one of accepted line items at In tab.
 * Check:  A margin call with Pledge Completed as Line Item Group Status.
 *
 */
object Case4 extends Ease with MarginCallOperation with LineItemOperation {

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
    completeLineItem(Allocate, In, 1) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    completeLineItem(Allocate, Out, 1) :::
    checkLineItemGroupStatus("Pledge Completed") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}