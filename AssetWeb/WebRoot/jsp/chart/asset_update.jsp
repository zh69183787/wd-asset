<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2014/11/18
  Time: 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<div class="m10">
    <table class="table_6">
        <tbody><tr>
            <td >
                <div class="bor"  id="chart3"></div>
            </td>
            <td >
                <div class="bor" id="chart4"></div>
            </td>
        </tr>
        <tr>
            <td>
                <div class="bor" id="chart5"></div>
            </td>
            <td>
                <div class="bor" id="chart6"></div>
            </td>
        </tr>
        </tbody></table>
</div>
    <ul class="clearfix" id="assetUpdateKpi">
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">本年总投资金额</h5>
                    <span class="fr pt24 mr5">万元</span>
                    <h1 class="fr" style="font-size:16px;">98</h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">累计总投资金额</h5>
                    <span class="fr pt24 mr5">万元</span>
                    <h1 class="fr" style="font-size:16px;">98</h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">总投资变化比率</h5>
                    <h1 class="fr mr5" style="font-size:16px;">12%</h1>
                </div>
            </div>
        </li>
        <%--<li class="gray_border counter  w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">自有资金占比</h5>
                    <h1 class="fr mr5">12%</h1>
                </div>
            </div>
        </li>
        --%>
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">大修资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter  w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">新增资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">更新资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
        <%--<li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">即将大修资产占比</h5>
                    <h1 class="fr mr5">12%</h1>
                </div>
            </div>
        </li>
        --%><%--<li class="gray_border counter  w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">实际大修资产占比</h5>
                    <h1 class="fr mr5">12%</h1>
                </div>
            </div>
        </li>
        --%><li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">正常大修资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">提前大修资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
        <li class="gray_border counter  w33p fl">
            <div class="block">
                <div class="corner h56 clearfix">
                    <h5 class="p8 fl">延时大修资产占比</h5>
                    <h1 class="fr mr5" style="font-size:16px;"></h1>
                </div>
            </div>
        </li>
    </ul>
