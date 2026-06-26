<template>
  <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
    <el-form-item label="客户名称" prop="customerName">
      <el-input v-model="form.customerName" placeholder="请输入"/>
    </el-form-item>
    <el-form-item label="联系人" prop="contact">
      <el-input v-model="form.contact" placeholder="请输入"/>
    </el-form-item>
    <el-form-item label="联系电话" prop="phone">
      <el-input v-model="form.phone" placeholder="请输入"/>
    </el-form-item>
    <el-form-item label="地址" prop="address">
      <el-input type="textarea" v-model="form.address" rows="3"/>
    </el-form-item>
  </el-form>
</template>

<script>
import { addCustomer, updateCustomer, getCustomer } from "@/api/system/customer";

export default {
  name: "SaleCustomerForm",
  props: { id: [String, Number] },
  data() {
    return {
      form: {},
      rules: { customerName: [{ required: true, message: "必填", trigger: "blur" }] }
    };
  },
  watch: { id: { handler: "getInfo", immediate: true } },
  methods: {
    async getInfo() {
      if (this.id) {
        const res = await getCustomer(this.id);
        this.form = res;
      } else {
        this.form = { customerName: "", contact: "", phone: "", address: "" };
      }
    },
    async submit() {
      await this.$refs.formRef.validate();
      this.id ? await updateCustomer(this.form) : await addCustomer(this.form);
    }
  }
};
</script>