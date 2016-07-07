package com.ssc.cm
package cmw2517

/**
 * *
 * accept line item
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Collateral Substitution as Call Business Type.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call if it is Client Call.
 * Action: Accept line item at In tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call or Pledged for Counterparty Call.
 * Action: Accept line item at Out tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Create line item at In tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call or Pledged for Counterparty Call.
 * Action: Cancel created line item.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Create line item at Out tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call or Pledged for Counterparty Call.
 * Action: Delete created line item.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Create line item at In tab.
 * Check:  A margin call with Pledge Generated as Line Item Group Status for Client Call or Pledged for Counterparty Call.
 * Action: Accept created line item.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Agree margin call.
 * Action: Complete accepted line item at Out tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 * Action: Complete one of accepted line items at In tab.
 * Check:  A margin call with Pledge Accepted as Line Item Group Status.
 *
 */
object Case3 extends Ease with MarginCallOperation with LineItemOperation {

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
    acceptLineItem(Agree, In, 1) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    acceptLineItem(Agree, Out, 1) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    insertLineItem(Agree, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    cancelLineItem(Agree, In, 2) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    insertLineItem(Agree, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    deleteLineItem(Agree, In, 2) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    insertLineItem(Agree, In, Isin, "000000CCYUSD", 100) :::
    checkLineItemGroupStatus("Pledge Generated") :::
    acceptLineItem(Agree, In, 2) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    agreeMarginCall :::
    completeLineItem(Allocate, In, 1) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    completeLineItem(Allocate, Out, 1) :::
    checkLineItemGroupStatus("Pledge Accepted") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}