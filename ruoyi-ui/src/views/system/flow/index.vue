<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="流水编号" prop="flowNo">
        <el-input
          v-model="queryParams.flowNo"
          placeholder="请输入流水编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="productName">
        <el-input
          v-model="queryParams.productName"
          placeholder="请输入产品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品规格" prop="productSpec">
        <el-input
          v-model="queryParams.productSpec"
          placeholder="请输入产品规格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice">
        <el-input
          v-model="queryParams.unitPrice"
          placeholder="请输入单价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单笔总价" prop="totalAmount">
        <el-input
          v-model="queryParams.totalAmount"
          placeholder="请输入单笔总价"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该产品累计入库数量" prop="productTotalIn">
        <el-input
          v-model="queryParams.productTotalIn"
          placeholder="请输入该产品累计入库数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该产品累计入库金额" prop="productTotalInAmount">
        <el-input
          v-model="queryParams.productTotalInAmount"
          placeholder="请输入该产品累计入库金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该产品累计出库数量" prop="productTotalOut">
        <el-input
          v-model="queryParams.productTotalOut"
          placeholder="请输入该产品累计出库数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该产品累计出库金额" prop="productTotalOutAmount">
        <el-input
          v-model="queryParams.productTotalOutAmount"
          placeholder="请输入该产品累计出库金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该客户累计出库数量" prop="customerTotalOut">
        <el-input
          v-model="queryParams.customerTotalOut"
          placeholder="请输入该客户累计出库数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="该客户累计出库金额" prop="customerTotalOutAmount">
        <el-input
          v-model="queryParams.customerTotalOutAmount"
          placeholder="请输入该客户累计出库金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年月" prop="yearMonth">
        <el-input
          v-model="queryParams.yearMonth"
          placeholder="请输入年月"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联客户名称" prop="customerName">
        <el-input
          v-model="queryParams.customerName"
          placeholder="请输入关联客户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联订单编号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入关联订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作时间" prop="operateTime">
        <el-date-picker clearable
          v-model="queryParams.operateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择操作时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="操作员" prop="operator">
        <el-input
          v-model="queryParams.operator"
          placeholder="请输入操作员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:flow:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:flow:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:flow:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:flow:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="flowList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" />
      <el-table-column label="流水编号" align="center" prop="flowNo" />
      <el-table-column label="产品名称" align="center" prop="productName" />
      <el-table-column label="产品类型" align="center" prop="productType" />
      <el-table-column label="产品规格" align="center" prop="productSpec" />
      <el-table-column label="进出库类型：1=入库，2=出库" align="center" prop="inoutType" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="单价" align="center" prop="unitPrice" />
      <el-table-column label="单笔总价" align="center" prop="totalAmount" />
      <el-table-column label="该产品累计入库数量" align="center" prop="productTotalIn" />
      <el-table-column label="该产品累计入库金额" align="center" prop="productTotalInAmount" />
      <el-table-column label="该产品累计出库数量" align="center" prop="productTotalOut" />
      <el-table-column label="该产品累计出库金额" align="center" prop="productTotalOutAmount" />
      <el-table-column label="该客户累计出库数量" align="center" prop="customerTotalOut" />
      <el-table-column label="该客户累计出库金额" align="center" prop="customerTotalOutAmount" />
      <el-table-column label="年月" align="center" prop="yearMonth" />
      <el-table-column label="关联客户名称" align="center" prop="customerName" />
      <el-table-column label="关联订单编号" align="center" prop="orderNo" />
      <el-table-column label="操作时间" align="center" prop="operateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.operateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作员" align="center" prop="operator" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:flow:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:flow:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改钢球进出库流水（含统计字段）对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流水编号" prop="flowNo">
          <el-input v-model="form.flowNo" placeholder="请输入流水编号" />
        </el-form-item>
        <el-form-item label="产品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品名称" />
        </el-form-item>
        <el-form-item label="产品规格" prop="productSpec">
          <el-input v-model="form.productSpec" placeholder="请输入产品规格" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="单价" prop="unitPrice">
          <el-input v-model="form.unitPrice" placeholder="请输入单价" />
        </el-form-item>
        <el-form-item label="单笔总价" prop="totalAmount">
          <el-input v-model="form.totalAmount" placeholder="请输入单笔总价" />
        </el-form-item>
        <el-form-item label="该产品累计入库数量" prop="productTotalIn">
          <el-input v-model="form.productTotalIn" placeholder="请输入该产品累计入库数量" />
        </el-form-item>
        <el-form-item label="该产品累计入库金额" prop="productTotalInAmount">
          <el-input v-model="form.productTotalInAmount" placeholder="请输入该产品累计入库金额" />
        </el-form-item>
        <el-form-item label="该产品累计出库数量" prop="productTotalOut">
          <el-input v-model="form.productTotalOut" placeholder="请输入该产品累计出库数量" />
        </el-form-item>
        <el-form-item label="该产品累计出库金额" prop="productTotalOutAmount">
          <el-input v-model="form.productTotalOutAmount" placeholder="请输入该产品累计出库金额" />
        </el-form-item>
        <el-form-item label="该客户累计出库数量" prop="customerTotalOut">
          <el-input v-model="form.customerTotalOut" placeholder="请输入该客户累计出库数量" />
        </el-form-item>
        <el-form-item label="该客户累计出库金额" prop="customerTotalOutAmount">
          <el-input v-model="form.customerTotalOutAmount" placeholder="请输入该客户累计出库金额" />
        </el-form-item>
        <el-form-item label="年月" prop="yearMonth">
          <el-input v-model="form.yearMonth" placeholder="请输入年月" />
        </el-form-item>
        <el-form-item label="关联客户名称" prop="customerName">
          <el-input v-model="form.customerName" placeholder="请输入关联客户名称" />
        </el-form-item>
        <el-form-item label="关联订单编号" prop="orderNo">
          <el-input v-model="form.orderNo" placeholder="请输入关联订单编号" />
        </el-form-item>
        <el-form-item label="操作时间" prop="operateTime">
          <el-date-picker clearable
            v-model="form.operateTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择操作时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="操作员" prop="operator">
          <el-input v-model="form.operator" placeholder="请输入操作员" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFlow, getFlow, delFlow, addFlow, updateFlow } from "@/api/system/flow"

export default {
  name: "Flow",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 钢球进出库流水（含统计字段）表格数据
      flowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        flowNo: null,
        productName: null,
        productType: null,
        productSpec: null,
        inoutType: null,
        quantity: null,
        unitPrice: null,
        totalAmount: null,
        productTotalIn: null,
        productTotalInAmount: null,
        productTotalOut: null,
        productTotalOutAmount: null,
        customerTotalOut: null,
        customerTotalOutAmount: null,
        yearMonth: null,
        customerName: null,
        orderNo: null,
        operateTime: null,
        operator: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        flowNo: [
          { required: true, message: "流水编号不能为空", trigger: "blur" }
        ],
        productName: [
          { required: true, message: "产品名称不能为空", trigger: "blur" }
        ],
        productType: [
          { required: true, message: "产品类型不能为空", trigger: "change" }
        ],
        productSpec: [
          { required: true, message: "产品规格不能为空", trigger: "blur" }
        ],
        inoutType: [
          { required: true, message: "进出库类型：1=入库，2=出库不能为空", trigger: "change" }
        ],
        quantity: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        operateTime: [
          { required: true, message: "操作时间不能为空", trigger: "blur" }
        ],
        operator: [
          { required: true, message: "操作员不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询钢球进出库流水（含统计字段）列表 */
    getList() {
      this.loading = true
      listFlow(this.queryParams).then(response => {
        this.flowList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    // 取消按钮
    cancel() {
      this.open = false
      this.reset()
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        flowNo: null,
        productName: null,
        productType: null,
        productSpec: null,
        inoutType: null,
        quantity: null,
        unitPrice: null,
        totalAmount: null,
        productTotalIn: null,
        productTotalInAmount: null,
        productTotalOut: null,
        productTotalOutAmount: null,
        customerTotalOut: null,
        customerTotalOutAmount: null,
        yearMonth: null,
        customerName: null,
        orderNo: null,
        operateTime: null,
        operator: null,
        remark: null,
        createTime: null,
        updateTime: null
      }
      this.resetForm("form")
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加钢球进出库流水（含统计字段）"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getFlow(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改钢球进出库流水（含统计字段）"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFlow(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addFlow(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除钢球进出库流水（含统计字段）编号为"' + ids + '"的数据项？').then(function() {
        return delFlow(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/flow/export', {
        ...this.queryParams
      }, `flow_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
