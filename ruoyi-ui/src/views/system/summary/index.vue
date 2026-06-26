<template>
  <div class="app-container">
    <!-- 新增：图表区域 -->
    <el-card class="chart-card" style="margin-bottom: 20px;">
      <div slot="header" class="clearfix">
        <span>月度销售趋势图</span>
        <el-select 
          v-model="chartYear" 
          placeholder="选择年份" 
          style="width: 120px; float: right;" 
          @change="loadChartData"
        >
          <el-option label="2024" value="2024"></el-option>
          <el-option label="2025" value="2025"></el-option>
          <el-option label="2026" value="2026"></el-option>
        </el-select>
      </div>
      <div id="saleChart" style="width: 100%; height: 400px;"></div>
    </el-card>

    <!-- 原有查询表单（保留） -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年份" prop="year">
        <el-input
          v-model="queryParams.year"
          placeholder="请输入年份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月份" prop="month">
        <el-input
          v-model="queryParams.month"
          placeholder="请输入月份"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月度总销售量" prop="totalSaleNum">
        <el-input
          v-model="queryParams.totalSaleNum"
          placeholder="请输入月度总销售量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="月度总营业额" prop="totalSaleAmount">
        <el-input
          v-model="queryParams.totalSaleAmount"
          placeholder="请输入月度总营业额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 原有操作按钮（保留） -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:summary:add']"
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
          v-hasPermi="['system:summary:edit']"
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
          v-hasPermi="['system:summary:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:summary:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 原有表格（保留） -->
    <el-table v-loading="loading" :data="summaryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="汇总ID" align="center" prop="id" />
      <el-table-column label="年份" align="center" prop="year" />
      <el-table-column label="月份" align="center" prop="month" />
      <el-table-column label="月度总销售量" align="center" prop="totalSaleNum" />
      <el-table-column label="月度总营业额" align="center" prop="totalSaleAmount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:summary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:summary:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <!-- 原有分页（保留） -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 原有弹窗（保留） -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年份" prop="year">
          <el-input v-model="form.year" placeholder="请输入年份" />
        </el-form-item>
        <el-form-item label="月份" prop="month">
          <el-input v-model="form.month" placeholder="请输入月份" />
        </el-form-item>
        <el-form-item label="月度总销售量" prop="totalSaleNum">
          <el-input v-model="form.totalSaleNum" placeholder="请输入月度总销售量" />
        </el-form-item>
        <el-form-item label="月度总营业额" prop="totalSaleAmount">
          <el-input v-model="form.totalSaleAmount" placeholder="请输入月度总营业额" />
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
import { listSummary, getSummary, delSummary, addSummary, updateSummary } from "@/api/system/summary"
// 新增：引入ECharts
import * as echarts from 'echarts'

export default {
  name: "Summary",
  data() {
    return {
      // 原有数据（保留）
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      summaryList: [],
      title: "",
      open: false,
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        year: null,
        month: null,
        totalSaleNum: null,
        totalSaleAmount: null,
      },
      form: {},
      rules: {
        year: [
          { required: true, message: "年份不能为空", trigger: "blur" }
        ],
        month: [
          { required: true, message: "月份不能为空", trigger: "blur" }
        ],
        totalSaleNum: [
          { required: true, message: "月度总销售量不能为空", trigger: "blur" }
        ],
        totalSaleAmount: [
          { required: true, message: "月度总营业额不能为空", trigger: "blur" }
        ]
      },
      // 新增：图表相关数据
      chartYear: '2025', // 默认显示2025年数据
      saleChart: null // ECharts实例
    }
  },
  created() {
    this.getList()
  },
  // 新增：页面挂载时初始化图表
  mounted() {
    this.initChart()
    this.loadChartData()
  },
  // 新增：页面销毁时销毁图表，避免内存泄漏
  beforeDestroy() {
    if (this.saleChart) {
      this.saleChart.dispose()
    }
  },
  methods: {
    /** 查询钢球月度销售汇总（图专用）列表 */
    getList() {
      this.loading = true
      listSummary(this.queryParams).then(response => {
        this.summaryList = response.rows
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
        year: null,
        month: null,
        totalSaleNum: null,
        totalSaleAmount: null,
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
      this.title = "添加钢球月度销售汇总（图专用）"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getSummary(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改钢球月度销售汇总（图专用）"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSummary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
              // 新增：修改后刷新图表
              this.loadChartData()
            })
          } else {
            addSummary(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
              // 新增：新增后刷新图表
              this.loadChartData()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除钢球月度销售汇总（图专用）编号为"' + ids + '"的数据项？').then(function() {
        return delSummary(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
        // 新增：删除后刷新图表
        this.loadChartData()
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/summary/export', {
        ...this.queryParams
      }, `summary_${new Date().getTime()}.xlsx`)
    },

    // ===================== 新增：图表相关方法 =====================
    /** 初始化图表 */
    initChart() {
      // 创建ECharts实例
      this.saleChart = echarts.init(document.getElementById('saleChart'))
      // 图表基础配置
      const baseOption = {
        // 鼠标悬浮提示
        tooltip: {
          trigger: 'axis',
          axisPointer: { type: 'shadow' }
        },
        // 图例
        legend: {
          data: ['月度销售量（个）', '月度营业额（元）'],
          top: 0
        },
        // 网格
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        // X轴：1-12月
        xAxis: {
          type: 'category',
          data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
        },
        // Y轴：双轴（左侧销售量，右侧营业额）
        yAxis: [
          {
            type: 'value',
            name: '销售量（个）',
            axisLabel: { formatter: '{value}' }
          },
          {
            type: 'value',
            name: '营业额（元）',
            position: 'right',
            axisLabel: { formatter: '{value}' }
          }
        ],
        // 系列数据（初始为空）
        series: [
          {
            name: '月度销售量（个）',
            type: 'bar', // 柱状图
            data: new Array(12).fill(0)
          },
          {
            name: '月度营业额（元）',
            type: 'line', // 折线图
            yAxisIndex: 1, // 关联右侧Y轴
            data: new Array(12).fill(0),
            smooth: true // 折线平滑
          }
        ]
      }
      // 设置基础配置
      this.saleChart.setOption(baseOption)
      
      // 自适应窗口大小
      window.addEventListener('resize', () => {
        this.saleChart.resize()
      })
    },

    /** 加载指定年份的图表数据 */
    loadChartData() {
      // 构造查询参数：只查指定年份，不分页
      const chartQuery = {
        year: this.chartYear,
        pageNum: 1,
        pageSize: 100 // 足够大的数值，确保查完所有数据
      }
      
      // 查询该年份所有月度数据
      listSummary(chartQuery).then(response => {
        // 初始化12个月数据为0
        const saleNumData = new Array(12).fill(0)
        const saleAmountData = new Array(12).fill(0)
        
        // 填充数据（month是1-12，对应数组索引0-11）
        response.rows.forEach(item => {
          const monthIndex = item.month - 1
          saleNumData[monthIndex] = item.totalSaleNum || 0
          saleAmountData[monthIndex] = item.totalSaleAmount || 0
        })
        
        // 更新图表数据
        this.saleChart.setOption({
          series: [
            { name: '月度销售量（个）', data: saleNumData },
            { name: '月度营业额（元）', data: saleAmountData }
          ]
        })
      })
    }
  }
}
</script>

<style scoped>
/* 新增：图表卡片样式 */
.chart-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border-radius: 4px;
}
</style>