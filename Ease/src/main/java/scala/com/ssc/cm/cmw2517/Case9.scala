package com.ssc.cm
package cmw2517

/**
 * *
 * issue substitution
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Client Collateral Account as Call Account.
 * Action: Create one line item at In tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Client Collateral Account as Call Account.
 * Action: Create one line item at Out tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Client Collateral Account as Call Account.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pledged Sent as Line Item Group Status.
 * Action: Enter Margin Call Manager page.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Counterparty Collateral Account as Call Account.
 * Action: Create one line item at In tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pending Pledge as Line Item Group Status.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Counterparty Collateral Account as Call Account.
 * Action: Create one line item at Out tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pledged Sent as Line Item Group Status.
 * Action: Create margin call with Client Call as Call Type, Collateral Substitution as Call Business Type, Counterparty Collateral Account as Call Account.
 * Action: Create one line item at each of In and Out tab.
 * Action: Issue created margin call.
 * Check:  A margin call with Pledged Sent as Line Item Group Status.
 *
 */
object Case9 extends Ease with MarginCallOperation with LineItemOperation {

  val strUrl = "http://10.248.98.82:8881/CM_Portal/"
  val agrId = 77044
  val agrName = "CMW-2188-SWAP1"

  val actions = withDelay(login(nameOf(strUrl)) :::
    enterMarginCall :::
    filterMarginCall(nameOf(agrId)) :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    addSubstitution(nameOf(agrName), ClientCall, ClientCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pledged Sent") :::
    addSubstitution(nameOf(agrName), ClientCall, CpCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pending Pledge") :::
    addSubstitution(nameOf(agrName), ClientCall, CpCollAccount) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pledged Sent") :::
    addSubstitution(nameOf(agrName), ClientCall, CpCollAccount) :::
    insertLineItem(Issue, In, Isin, "000000CCYUSD", 100) :::
    insertLineItem(Issue, Out, Isin, "000000CCYUSD", 100) :::
    issueMarginCall(1) :::
    checkLineItemGroupStatus("Pledged Sent") :::
    CloseBrowser :: Nil, 2)

  val data = named(strUrl) :: named(agrId) :: named(agrName) :: Nil

  val errorHandler = "Next_TestCase"

  val writeTo = "TestScripts"

}