package com.ssc.cm
package cmw2517

/**
 * *
 * reject line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Reject line item at In tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Accept line item at Out tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Create line item at In tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Cancel created line item.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Create line item at In tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Accept created line item.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Agree margin call.
 * Action: Complete accepted line item at Out tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Complete accepted line item at In tab.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 * Action: Send line item.
 * Check:  A margin call with Pledge Rejected as Line Item Group Status.
 *
 */
object Case2 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 200) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 200) :::
    issueMarginCall(1) :::
    rejectLineItem(Agree, In, Other("test")) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    acceptLineItem(Agree, Out, 1) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    insertLineItem(Agree, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    cancelLineItem(Agree, In, 2) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    insertLineItem(Agree, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    acceptLineItem(Agree, In, 2) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    agreeMarginCall :::
    completeLineItem(Allocate, In, 2) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    completeLineItem(Allocate, Out, 1) :::
    checkLineItemGroupStatus("Pledge Rejected") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}