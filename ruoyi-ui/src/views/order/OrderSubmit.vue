<template>
  <div class="app-container">
    <el-card shadow="never" class="page-card-container">
      <div slot="header" class="clearfix">
        <span>钢球产品订购单</span>
      </div>

      <!-- 下单表单 -->
      <el-form
        ref="orderForm"
        :model="orderForm"
        :rules="orderRules"
        label-width="100px"
        class="order-form"
      >
        <el-row :gutter="20">
          <!-- 客户信息区域 -->
          <el-col :span="12">
            <el-form-item label="客户姓名" prop="customerName">
              <el-input
                v-model="orderForm.customerName"
                placeholder="请输入您的姓名"
                maxlength="30"
                show-word-limit
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="联系电话" prop="customerPhone">
              <el-input
                v-model="orderForm.customerPhone"
                placeholder="请输入您的手机号"
                maxlength="11"
                show-word-limit
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="联系地址" prop="customerAddress">
              <el-input
                v-model="orderForm.customerAddress"
                placeholder="请输入详细收货地址"
                maxlength="200"
                show-word-limit
                type="textarea"
                :rows="2"
              />
            </el-form-item>
          </el-col>

          <!-- 产品信息区域 -->
          <el-col :span="12">
            <el-form-item label="产品类型" prop="productType">
              <el-select
                v-model="orderForm.productType"
                placeholder="请选择产品类型"
                style="width: 100%"
                @change="handleProductTypeChange"
              >
                <el-option label="轴承钢球" value="轴承钢球" />
                <el-option label="不锈钢球" value="不锈钢球" />
                <el-option label="铬钢球" value="铬钢球" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="产品规格" prop="productSpec">
              <el-select
                v-model="orderForm.productSpec"
                placeholder="请先选择产品类型"
                style="width: 100%"
                @change="handleProductSpecChange"
                :disabled="!orderForm.productType"
              >
                <el-option
                  v-for="spec in productSpecList"
                  :key="spec.value"
                  :label="spec.label"
                  :value="spec.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="订购数量（件）" prop="productNum">
              <el-input-number
                v-model="orderForm.productNum"
                :min="1"
                :max="9999"
                placeholder="请输入订购数量"
                style="width: 100%"
                @change="calcTotalPrice"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单价（元/件）" prop="unitPrice">
              <el-input
                v-model="orderForm.unitPrice"
                placeholder="产品单价"
                disabled
                style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="订单总价（元）" prop="totalPrice">
              <el-input
                v-model="orderForm.totalPrice"
                placeholder="订单总价"
                disabled
                style="width: 100%"
              />
            </el-form-item>
          </el-col>

          <!-- 备注信息 -->
          <el-col :span="24">
            <el-form-item label="备注说明" prop="remark">
              <el-input
                v-model="orderForm.remark"
                placeholder="请输入特殊要求（选填）"
                maxlength="500"
                show-word-limit
                type="textarea"
                :rows="3"
              />
            </el-form-item>
          </el-col> <!-- 补全：备注信息的el-col闭合标签 -->

          <!-- 提交按钮 -->
          <el-col :span="24" class="btn-group">
            <el-form-item>
              <el-button type="primary" @click="submitOrder" :loading="submitLoading">
                提交订单
              </el-button>
              <el-button @click="resetForm">重置</el-button>
            </el-form-item>
          </el-col> <!-- 补全：按钮区域的el-col闭合标签 -->
        </el-row> <!-- 补全：el-row闭合标签 -->
      </el-form> <!-- 补全：el-form闭合标签 -->
    </el-card> <!-- 补全：el-card闭合标签 -->
  </div> <!-- 补全：最外层div闭合标签 -->
</template>

<script>
import { submitOrder } from "@/api/order"; // 引入订单提交接口

export default {
  name: "OrderSubmit",
  data() {
    return {
      // 表单数据
      orderForm: {
        customerName: "",
        customerPhone: "",
        customerAddress: "",
        productType: "", // 产品类型（轴承钢球/不锈钢球/铬钢球）
        productSpec: "", // 产品规格（尺寸）
        productNum: 1,   // 订购数量
        unitPrice: "",   // 单价
        totalPrice: "",  // 总价
        remark: "",
      },
      // 表单验证规则
      orderRules: {
        customerName: [
          { required: true, message: "请输入客户姓名", trigger: "blur" },
        ],
        customerPhone: [
          { required: true, message: "请输入联系电话", trigger: "blur" },
          {
            pattern: /^1[3-9]\d{9}$/,
            message: "请输入正确的手机号",
            trigger: "blur",
          },
        ],
        customerAddress: [
          { required: true, message: "请输入联系地址", trigger: "blur" },
        ],
        productType: [
          { required: true, message: "请选择产品类型", trigger: "change" },
        ],
        productSpec: [
          { required: true, message: "请选择产品规格", trigger: "change" },
        ],
        productNum: [
          { required: true, message: "请输入订购数量", trigger: "blur" },
        ],
      },
      // 提交加载状态
      submitLoading: false,
      // 产品规格列表（根据类型动态切换）
      productSpecList: [],
      // 产品价格映射表（类型+规格 → 单价）
      productPriceMap: {
        "轴承钢球": {
          "φ6mm": 80,
          "φ8mm": 95,
          "φ10mm": 110,
          "φ12mm": 130,
          "φ15mm": 160
        },
        "不锈钢球": {
          "φ6mm": 60,
          "φ8mm": 75,
          "φ10mm": 90,
          "φ12mm": 110,
          "φ15mm": 140
        },
        "铬钢球": {
          "φ6mm": 100,
          "φ8mm": 120,
          "φ10mm": 140,
          "φ12mm": 160,
          "φ15mm": 190
        }
      },
      // 产品规格选项映射
      productSpecMap: {
        "轴承钢球": [
          { label: "φ6mm", value: "φ6mm" },
          { label: "φ8mm", value: "φ8mm" },
          { label: "φ10mm", value: "φ10mm" },
          { label: "φ12mm", value: "φ12mm" },
          { label: "φ15mm", value: "φ15mm" }
        ],
        "不锈钢球": [
          { label: "φ6mm", value: "φ6mm" },
          { label: "φ8mm", value: "φ8mm" },
          { label: "φ10mm", value: "φ10mm" },
          { label: "φ12mm", value: "φ12mm" },
          { label: "φ15mm", value: "φ15mm" }
        ],
        "铬钢球": [
          { label: "φ6mm", value: "φ6mm" },
          { label: "φ8mm", value: "φ8mm" },
          { label: "φ10mm", value: "φ10mm" },
          { label: "φ12mm", value: "φ12mm" },
          { label: "φ15mm", value: "φ15mm" }
        ]
      }
    };
  },
  methods: {
    /**
     * 切换产品类型，更新规格列表
     */
    handleProductTypeChange(val) {
      // 清空原有规格选择
      this.orderForm.productSpec = "";
      this.orderForm.unitPrice = "";
      this.orderForm.totalPrice = "";
      // 更新规格列表
      this.productSpecList = this.productSpecMap[val] || [];
    },
    /**
     * 切换产品规格，自动填充单价
     */
    handleProductSpecChange(val) {
      if (this.orderForm.productType && val) {
        // 根据类型+规格获取单价
        this.orderForm.unitPrice = this.productPriceMap[this.orderForm.productType][val];
        // 计算总价
        this.calcTotalPrice();
      }
    },
    /**
     * 计算订单总价
     */
    calcTotalPrice() {
      if (this.orderForm.unitPrice && this.orderForm.productNum) {
        this.orderForm.totalPrice = (
          this.orderForm.unitPrice * this.orderForm.productNum
        ).toFixed(2);
      }
    },
    /**
     * 提交订单
     */
    submitOrder() {
      this.$refs.orderForm.validate((valid) => {
        if (valid) {
          this.submitLoading = true;
          // 构造提交参数（拼接产品名称：类型+规格）
          const submitData = {
            customerName: this.orderForm.customerName,
            customerPhone: this.orderForm.customerPhone,
            customerAddress: this.orderForm.customerAddress,
            productName: `${this.orderForm.productType}(${this.orderForm.productSpec})`, // 拼接产品全称
            productType: this.orderForm.productType,
            productSpec: this.orderForm.productSpec,
            productNum: this.orderForm.productNum,
            unitPrice: this.orderForm.unitPrice,
            totalPrice: this.orderForm.totalPrice,
            remark: this.orderForm.remark,
            // 生成订单编号（可根据业务规则自定义）
            orderNo: "ORD" + Date.now() + Math.floor(Math.random() * 1000),
          };
          // 调用提交接口
          submitOrder(submitData)
            .then((res) => {
              this.$message.success("订单提交成功！等待管理员审核");
              // 重置表单
              this.resetForm();
            })
            .catch((err) => {
              this.$message.error("订单提交失败：" + (err.msg || "系统异常"));
            })
            .finally(() => {
              this.submitLoading = false;
            });
        }
      });
    },
    /**
     * 重置表单
     */
    resetForm() {
      this.$refs.orderForm.resetFields();
      this.orderForm = {
        customerName: "",
        customerPhone: "",
        customerAddress: "",
        productType: "",
        productSpec: "",
        productNum: 1,
        unitPrice: "",
        totalPrice: "",
        remark: "",
      };
      this.productSpecList = [];
    },
  },
};
</script>

<style scoped>
.page-card-container {
  padding: 20px;
}

.order-form {
  margin-top: 20px;
}

.btn-group {
  text-align: center;
  margin-top: 30px;
}

.el-form-item {
  margin-bottom: 20px;
}
</style>