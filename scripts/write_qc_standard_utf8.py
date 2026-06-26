# -*- coding: utf-8 -*-
"""Write QC standard UI/Java sources with correct UTF-8 Chinese."""
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

VUE = r'''<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="\u6807\u51c6\u540d\u79f0" prop="standardName">
        <el-input v-model="queryParams.standardName" placeholder="\u8bf7\u8f93\u5165\u6807\u51c6\u540d\u79f0" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="\u5c3a\u5bf8\u548c\u7cbe\u5ea6" prop="sizePrecision">
        <el-input v-model="queryParams.sizePrecision" placeholder="\u8bf7\u8f93\u5165\u5c3a\u5bf8\u548c\u7cbe\u5ea6" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="\u8868\u9762\u7c97\u7cd9\u5ea6" prop="surfaceRoughness">
        <el-input v-model="queryParams.surfaceRoughness" placeholder="\u8bf7\u8f93\u5165\u8868\u9762\u7c97\u7cd9\u5ea6" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="\u786c\u5ea6" prop="hardness">
        <el-input v-model="queryParams.hardness" placeholder="\u8bf7\u8f93\u5165\u786c\u5ea6" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="\u6750\u6599" prop="material">
        <el-input v-model="queryParams.material" placeholder="\u8bf7\u8f93\u5165\u6750\u6599" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="\u72b6\u6001" prop="status">
        <el-select v-model="queryParams.status" placeholder="\u8bf7\u9009\u62e9\u72b6\u6001" clearable>
          <el-option v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">\u641c\u7d22</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">\u91cd\u7f6e</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"><el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['system:qcStandard:add']">\u65b0\u589e</el-button></el-col>
      <el-col :span="1.5"><el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['system:qcStandard:edit']">\u4fee\u6539</el-button></el-col>
      <el-col :span="1.5"><el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['system:qcStandard:remove']">\u5220\u9664</el-button></el-col>
      <el-col :span="1.5"><el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['system:qcStandard:export']">\u5bfc\u51fa</el-button></el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    <el-table v-loading="loading" :data="qcStandardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="\u7f16\u53f7" align="center" prop="id" width="80" />
      <el-table-column label="\u6807\u51c6\u540d\u79f0" align="center" prop="standardName" min-width="120" show-overflow-tooltip />
      <el-table-column label="\u5c3a\u5bf8\u548c\u7cbe\u5ea6" align="center" prop="sizePrecision" min-width="140" show-overflow-tooltip />
      <el-table-column label="\u8868\u9762\u7c97\u7cd9\u5ea6" align="center" prop="surfaceRoughness" min-width="120" show-overflow-tooltip />
      <el-table-column label="\u786c\u5ea6" align="center" prop="hardness" min-width="100" show-overflow-tooltip />
      <el-table-column label="\u6750\u6599" align="center" prop="material" min-width="100" show-overflow-tooltip />
      <el-table-column label="\u72b6\u6001" align="center" prop="status" width="80">
        <template slot-scope="scope"><dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/></template>
      </el-table-column>
      <el-table-column label="\u5907\u6ce8" align="center" prop="remark" min-width="120" show-overflow-tooltip />
      <el-table-column label="\u64cd\u4f5c" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:qcStandard:edit']">\u4fee\u6539</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['system:qcStandard:remove']">\u5220\u9664</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    <el-dialog :title="title" :visible.sync="open" width="560px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="\u6807\u51c6\u540d\u79f0" prop="standardName"><el-input v-model="form.standardName" placeholder="\u8bf7\u8f93\u5165\u6807\u51c6\u540d\u79f0" /></el-form-item>
        <el-form-item label="\u5c3a\u5bf8\u548c\u7cbe\u5ea6" prop="sizePrecision"><el-input v-model="form.sizePrecision" type="textarea" :rows="2" placeholder="\u5982\uff1a\u03c610\u00b10.001mm" /></el-form-item>
        <el-form-item label="\u8868\u9762\u7c97\u7cd9\u5ea6" prop="surfaceRoughness"><el-input v-model="form.surfaceRoughness" placeholder="\u5982\uff1aRa0.025" /></el-form-item>
        <el-form-item label="\u786c\u5ea6" prop="hardness"><el-input v-model="form.hardness" placeholder="\u5982\uff1aHRC58-62" /></el-form-item>
        <el-form-item label="\u6750\u6599" prop="material"><el-input v-model="form.material" placeholder="\u5982\uff1aGCr15" /></el-form-item>
        <el-form-item label="\u72b6\u6001" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.sys_normal_disable" :key="dict.value" :label="dict.value">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="\u5907\u6ce8" prop="remark"><el-input v-model="form.remark" type="textarea" placeholder="\u8bf7\u8f93\u5165\u5907\u6ce8" /></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">\u786e \u5b9a</el-button>
        <el-button @click="cancel">\u53d6 \u6d88</el-button>
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
      rules: { standardName: [{ required: true, message: "\u6807\u51c6\u540d\u79f0\u4e0d\u80fd\u4e3a\u7a7a", trigger: "blur" }] }
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
    handleAdd() { this.reset(); this.open = true; this.title = "\u6dfb\u52a0\u8d28\u68c0\u6807\u51c6" },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getQcStandard(id).then(response => { this.form = response.data; this.open = true; this.title = "\u4fee\u6539\u8d28\u68c0\u6807\u51c6" })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateQcStandard(this.form).then(() => { this.$modal.msgSuccess("\u4fee\u6539\u6210\u529f"); this.open = false; this.getList() })
          } else {
            addQcStandard(this.form).then(() => { this.$modal.msgSuccess("\u65b0\u589e\u6210\u529f"); this.open = false; this.getList() })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('\u662f\u5426\u786e\u8ba4\u5220\u9664\u8d28\u68c0\u6807\u51c6\u7f16\u53f7\u4e3a"' + ids + '"\u7684\u6570\u636e\u9879\uff1f').then(function() {
        return delQcStandard(ids)
      }).then(() => { this.getList(); this.$modal.msgSuccess("\u5220\u9664\u6210\u529f") }).catch(() => {})
    },
    handleExport() {
      this.download('system/qcStandard/export', { ...this.queryParams }, `qcStandard_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
'''

JAVA_DOMAIN = r'''package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class QualityInspectionStandard extends BaseEntity
{
    private static final long serialVersionUID = 1L;
    private Long id;
    @Excel(name = "\u6807\u51c6\u540d\u79f0")
    private String standardName;
    @Excel(name = "\u5c3a\u5bf8\u548c\u7cbe\u5ea6")
    private String sizePrecision;
    @Excel(name = "\u8868\u9762\u7c97\u7cd9\u5ea6")
    private String surfaceRoughness;
    @Excel(name = "\u786c\u5ea6")
    private String hardness;
    @Excel(name = "\u6750\u6599")
    private String material;
    @Excel(name = "\u72b6\u6001", readConverterExp = "0=\u6b63\u5e38,1=\u505c\u7528")
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getStandardName() { return standardName; }
    public void setStandardName(String standardName) { this.standardName = standardName; }
    public String getSizePrecision() { return sizePrecision; }
    public void setSizePrecision(String sizePrecision) { this.sizePrecision = sizePrecision; }
    public String getSurfaceRoughness() { return surfaceRoughness; }
    public void setSurfaceRoughness(String surfaceRoughness) { this.surfaceRoughness = surfaceRoughness; }
    public String getHardness() { return hardness; }
    public void setHardness(String hardness) { this.hardness = hardness; }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId()).append("standardName", getStandardName())
            .append("sizePrecision", getSizePrecision()).append("surfaceRoughness", getSurfaceRoughness())
            .append("hardness", getHardness()).append("material", getMaterial())
            .append("status", getStatus()).append("remark", getRemark())
            .append("createBy", getCreateBy()).append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy()).append("updateTime", getUpdateTime()).toString();
    }
}
'''

def main():
    vue_path = ROOT / "ruoyi-ui" / "src" / "views" / "system" / "qcStandard" / "index.vue"
    java_path = ROOT / "ruoyi-system" / "src" / "main" / "java" / "com" / "ruoyi" / "system" / "domain" / "QualityInspectionStandard.java"
    vue_path.write_text(VUE.encode('utf-8').decode('unicode_escape'), encoding='utf-8')
    java_path.write_text(JAVA_DOMAIN.encode('utf-8').decode('unicode_escape'), encoding='utf-8')
    print("Wrote", vue_path)
    print("Wrote", java_path)

if __name__ == "__main__":
    main()
