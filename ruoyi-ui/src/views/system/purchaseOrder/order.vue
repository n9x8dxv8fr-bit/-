<template>
  <div class="app-container">
    <!-- 查询表单 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
      <el-form-item label="采购单号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入采购单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商ID" prop="supplierId">
        <el-input
          v-model="queryParams.supplierId"
          placeholder="请输入供应商ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
    </el-row>

    <!-- 表格 -->
    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="采购单号" align="center" prop="orderNo" />
      <el-table-column label="供应商ID" align="center" prop="supplierId" />
      <el-table-column label="总数量" align="center" prop="totalQuantity" />
      <el-table-column label="总金额" align="center" prop="totalAmount" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="采购时间" align="center" prop="purchaseTime" />
      <el-table-column label="操作人" align="center" prop="operator" />
      <el-table-column label="操作" align="center" class="option-col">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 弹窗 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="采购单号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入采购单号" />
        </el-form-item>
        <el-form-item label="供应商ID" prop="supplierId">
          <el-input v-model="form.supplierId" placeholder="请输入供应商ID" />
        </el-form-item>
        <el-form-item label="产品类型">
          <el-input v-model="form.productType" placeholder="与订购/销售一致，可空" />
        </el-form-item>
        <el-form-item label="产品规格">
          <el-input v-model="form.productSpec" placeholder="与订购/销售一致，可空" />
        </el-form-item>
        <el-form-item label="总数量">
          <el-input v-model="form.totalQuantity" placeholder="总数量" />
        </el-form-item>
        <el-form-item label="总金额">
          <el-input v-model="form.totalAmount" placeholder="总金额" />
        </el-form-item>
        <el-form-item label="状态">
          <el-input v-model="form.status" placeholder="状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// 引入API：函数名和API里的驼峰完全一致
import { listPurchaseOrder, getPurchaseOrder, delPurchaseOrder, addPurchaseOrder, updatePurchaseOrder } from "@/api/system/purchaseOrder"

export default {
  name: "PurchaseOrder", // 组件名统一驼峰
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      orderList: [],
      title: "",
      open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNo: null,
        supplierId: null,
        totalQuantity: null,
        totalAmount: null,
        status: null,
        purchaseTime: null,
        operator: null,
      },
      form: {},
      rules: {
        orderNo: [{ required: true, message: "采购单号不能为空", trigger: "blur" }],
        supplierId: [{ required: true, message: "供应商ID不能为空", trigger: "blur" }],
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 查询列表：适配若依框架返回结构（核心修改！）
    getList() {
      this.loading = true
      listPurchaseOrder(this.queryParams).then(response => {
        this.orderList = response.rows
        this.total = response.total
        this.loading = false
      }).catch(error => {
        console.error("采购订单列表请求失败：", error)
        this.loading = false
      })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        id: null,
        orderNo: null,
        supplierId: null,
        productType: null,
        productSpec: null,
        totalQuantity: null,
        totalAmount: null,
        status: null,
        purchaseTime: null,
        operator: null,
        remark: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加采购订单"
    },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getPurchaseOrder(id).then(response => {
        this.form = response
        this.open = true
        this.title = "修改采购订单"
      }).catch(error => {
        console.error("采购订单详情请求失败：", error)
      })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updatePurchaseOrder(this.form).then(() => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("采购订单修改失败：", error)
            })
          } else {
            addPurchaseOrder(this.form).then(() => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            }).catch(error => {
              console.error("采购订单新增失败：", error)
            })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除采购订单编号为"' + ids + '"的数据项？').then(() => {
        return delPurchaseOrder(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(error => {
        console.error("采购订单删除失败：", error)
      })
    },
    handleExport() {
      this.download('system/purchaseOrder/export', { ...this.queryParams }, `purchaseOrder_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>