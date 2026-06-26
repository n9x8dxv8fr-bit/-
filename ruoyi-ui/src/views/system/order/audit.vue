<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="88px">
      <el-form-item label="订单编号" prop="orderNo">
        <el-input v-model="queryParams.orderNo" placeholder="请输入订单编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="客户名称" prop="customerName">
        <el-input v-model="queryParams.customerName" placeholder="请输入客户名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="审核状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="待审核" value="待审核" />
          <el-option label="已审核" value="已审核" />
          <el-option label="已出库" value="已出库" />
          <el-option label="已驳回" value="已驳回" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList">
      <el-table-column label="ID" prop="id" width="80" />
      <el-table-column label="订单编号" prop="orderNo" />
      <el-table-column label="客户名称" prop="customerName" />
      <el-table-column label="联系电话" prop="customerPhone" />
      <el-table-column label="产品类型" prop="productType" />
      <el-table-column label="规格" prop="productSpec" />
      <el-table-column label="数量" prop="productNum" />
      <el-table-column label="总价" prop="totalPrice" />
      <el-table-column label="状态" prop="status" />
      <el-table-column label="下单时间" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status === '待审核'"
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleApprove(scope.row)"
          >通过</el-button>
          <el-button
            v-if="scope.row.status === '待审核'"
            size="mini"
            type="text"
            icon="el-icon-close"
            @click="handleReject(scope.row)"
          >驳回</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listOrderAudit, approveOrder, rejectOrder } from "@/api/order";

export default {
  name: "OrderAudit",
  data() {
    return {
      loading: false,
      showSearch: true,
      total: 0,
      orderList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        customerName: null,
        status: "待审核",
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      listOrderAudit(this.queryParams)
        .then((res) => {
          this.orderList = res.rows || [];
          this.total = res.total || 0;
        })
        .finally(() => {
          this.loading = false;
        });
    },
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.status = "待审核";
      this.handleQuery();
    },
    handleApprove(row) {
      this.$modal.prompt("请输入审核备注（可空）", "审核通过").then(({ value }) => {
        return approveOrder(row.id, value);
      }).then(() => {
        this.$modal.msgSuccess("审核通过，已生成销售明细");
        this.getList();
      });
    },
    handleReject(row) {
      this.$modal.prompt("请输入驳回原因", "审核驳回").then(({ value }) => {
        return rejectOrder(row.id, value);
      }).then(() => {
        this.$modal.msgSuccess("订单已驳回");
        this.getList();
      });
    },
  },
};
</script>
