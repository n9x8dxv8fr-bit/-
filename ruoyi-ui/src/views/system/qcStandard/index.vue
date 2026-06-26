<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="标准名称" prop="standardName">
        <el-input v-model="queryParams.standardName" placeholder="请输入标准名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="尺寸和精度" prop="sizePrecision">
        <el-input v-model="queryParams.sizePrecision" placeholder="请输入尺寸和精度" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="表面粗糙度" prop="surfaceRoughness">
        <el-input v-model="queryParams.surfaceRoughness" placeholder="请输入表面粗糙度" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="硬度" prop="hardness">
        <el-input v-model="queryParams.hardness" placeholder="请输入硬度" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="材料" prop="material">
        <el-input v-model="queryParams.material" placeholder="请输入材料" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:qcStandard:add']">新增</el-button></el-col>
      <el-col :span="1.5"><el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:qcStandard:edit']">修改</el-button></el-col>
      <el-col :span="1.5"><el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:qcStandard:remove']">删除</el-button></el-col>
      <el-col :span="1.5"><el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:qcStandard:export']">导出</el-button></el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="qcStandardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" width="80" />
      <el-table-column label="标准名称" align="center" prop="standardName" min-width="120" show-overflow-tooltip />
      <el-table-column label="尺寸和精度" align="center" prop="sizePrecision" min-width="140" show-overflow-tooltip />
      <el-table-column label="表面粗糙度" align="center" prop="surfaceRoughness" min-width="120" show-overflow-tooltip />
      <el-table-column label="硬度" align="center" prop="hardness" min-width="100" show-overflow-tooltip />
      <el-table-column label="材料" align="center" prop="material" min-width="100" show-overflow-tooltip />
      <el-table-column label="状态" align="center" prop="status" width="80">
        <template slot-scope="scope"><dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/></template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" min-width="120" show-overflow-tooltip />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:qcStandard:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:qcStandard:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    <el-dialog :title="title" :visible.sync="open" width="560px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="标准名称" prop="standardName"><el-input v-model="form.standardName" placeholder="请输入标准名称" /></el-form-item>
        <el-form-item label="尺寸和精度" prop="sizePrecision"><el-input v-model="form.sizePrecision" type="textarea" :rows="2" placeholder="如：φ10±0.001mm" /></el-form-item>
        <el-form-item label="表面粗糙度" prop="surfaceRoughness"><el-input v-model="form.surfaceRoughness" placeholder="如：Ra0.025" /></el-form-item>
        <el-form-item label="硬度" prop="hardness"><el-input v-model="form.hardness" placeholder="如：HRC58-62" /></el-form-item>
        <el-form-item label="材料" prop="material"><el-input v-model="form.material" placeholder="如：GCr15" /></el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark"><el-input v-model="form.remark" type="textarea" placeholder="请输入备注" /></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { listQcStandard, getQcStandard, delQcStandard, addQcStandard, updateQcStandard } from "@/api/system/qcStandard"
export default {
  name: "QcStandard",
  dicts: ['sys_normal_disable'],
  data() {
    return {
      loading: true, ids: [], single: true, multiple: true, showSearch: true, total: 0,
      qcStandardList: [], title: "", open: false,
      queryParams: { pageNum: 1, pageSize: 10, standardName: null, sizePrecision: null, surfaceRoughness: null, hardness: null, material: null, status: null },
      form: {},
      rules: { standardName: [{ required: true, message: "标准名称不能为空", trigger: "blur" }] }
    }
  },
  created() { this.getList() },
  methods: {
    getList() {
      this.loading = true
      listQcStandard(this.queryParams).then(response => {
        this.qcStandardList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    cancel() { this.open = false; this.reset() },
    reset() {
      this.form = { id: null, standardName: null, sizePrecision: null, surfaceRoughness: null, hardness: null, material: null, status: "0", remark: null }
      this.resetForm("form")
    },
    handleQuery() { this.queryParams.pageNum = 1; this.getList() },
    resetQuery() { this.resetForm("queryForm"); this.handleQuery() },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    handleAdd() { this.reset(); this.open = true; this.title = "添加质检标准" },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getQcStandard(id).then(response => { this.form = response.data; this.open = true; this.title = "修改质检标准" })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateQcStandard(this.form).then(() => { this.$modal.msgSuccess("修改成功"); this.open = false; this.getList() })
          } else {
            addQcStandard(this.form).then(() => { this.$modal.msgSuccess("新增成功"); this.open = false; this.getList() })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除质检标准编号为"' + ids + '"的数据项？').then(function() {
        return delQcStandard(ids)
      }).then(() => { this.getList(); this.$modal.msgSuccess("删除成功") }).catch(() => {})
    },
    handleExport() {
      this.download('system/qcStandard/export', { ...this.queryParams }, `qcStandard_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
