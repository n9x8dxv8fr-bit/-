<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <h2>亚东方钢球有限公司</h2>
        <p>
          <b>郓城县亚东方钢球有限公司位于山东省菏泽市郓城县经济开发区，公司占地面积约50亩，建筑面积20000多平方米，现有员工200余人，其中高级工程师5人，工程师20余人，技术人员30余人。公司拥有先进的生产设备和检测仪器，如：德国进口多功能数控滚齿机、数控磨床、精密球径测量仪、金相显微镜、硬度计等一批专业设备。公司通过了ISO9001：2015质量管理体系认证，并被评为“重合同守信用”企业、“山东省著名商标”、“山东省优质产品”等称号。
        </b></p>
        <p>
          <b>郓城县亚东方钢球有限公司是一家生产铬钢球、不锈钢为主的现代化企业，本公司自成立以来，就遵循着“优起点、优速度、优质量、优效益”的原则，依靠科学的管理，齐全的技术，诚信经营的敬业精神，赢得了用户的信赖，产品在航天航空工业、汽车工业、精密仪器、仪表、摩托车、轴承、医疗器械和食品加工机械等行业中得到广泛的应用，成为理想的配套产品。在时代经济发展滚滚潮流中，信用是用不完的资本。这一崇高宗旨，竭诚为新老客户提供优良的产品、体贴的服务。 郓城县亚东方钢球有限公司愿与社会各界同仁共同开拓钢球行业辉煌的未来。
          当前版本:</b> <span>v{{ version }}</span>
        </p>
       
        <p>
          <el-button
            size="mini"
            icon="el-icon-s-home"
            plain
            @click="goTarget('http://www.ydfgq.com/about-2.html')"
            >访问官网</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="24">
            <div class="welcome-card">
              <!-- 自动显示用户昵称（优先nickName，无则用userName） -->
              <h1 class="welcome-title">欢迎回来，{{ userName }}</h1>
              <p class="welcome-desc">我们将竭诚为您提供专业的钢球业务管理服务</p>
              
              <!-- 自动判断是否为管理员（匹配roles中的标识） -->
              <div class="admin-tips" v-if="isAdmin">
                <el-divider content-position="left" style="margin: 15px 0;">📊 业务速览</el-divider>
                <el-row :gutter="10">
                  <el-col :span="12">
                    <div class="tips-item">
                      <span class="tips-label">今日待办：</span>
                      <span class="tips-value"></span>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="tips-item">
                      <span class="tips-label">昨日销量：</span>
                      <span class="tips-value">{{ overview.yesterdaySales }}</span>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="tips-item">
                      <span class="tips-label">待审核订单：</span>
                      <span class="tips-value">{{ overview.pendingAuditOrders }}</span>
                    </div>
                  </el-col>
                  <el-col :span="12">
                    <div class="tips-item">
                      <span class="tips-label">库存预警：</span>
                      <span class="tips-value"></span>
                    </div>
                  </el-col>
                </el-row>
              </div>
            </div>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>联系信息</span>
          </div>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
                href="http://www.ydfgq.com/about-2.html"
                target="_blank"
                >http://www.ydfgq.com/about-2.html</el-link
              >
              <p>电话：13082985617</p>
            </p>
           
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：<a
                href="javascript:;"
                >/ *wyr</a
              >
            </p>
            <p>
              <i class="el-icon-money"></i> 支付宝：<a
                href="javascript:;"
                class="支付宝信息"
                >/ *wyr</a
              >
            </p>
          </div>
        </el-card>
      </el-col>
     
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>捐赠支持</span>
          </div>
          <div class="body">
            <img
              src="@/assets/images/money.jpg"
              alt="donate"
              width="100%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >感谢您的支持</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { getOrderOverview } from "@/api/order";

export default {
  name: "Index",
  computed: {
    // 自动从Vuex读取用户昵称（适配你的user.js字段）
    userName() {
      // 你的user.js中存储的是nickName/name（userName）
      const userState = this.$store.state.user || {};
      return userState.nickName || userState.name || '尊敬的用户';
    },
    // 自动判断是否为管理员（匹配roles中的标识）
    isAdmin() {
      const userState = this.$store.state.user || {};
      const roles = userState.roles || [];
      // 适配你的角色标识（若依默认是ROLE_ADMIN，可根据实际修改）
      // 比如你的管理员角色是"admin"/"ROLE_ADMIN"/"管理员"，都能匹配
      const roleStr = Array.isArray(roles) ? roles.join(',') : roles;
      return roleStr.includes('ROLE_ADMIN') || roleStr.includes('admin') || roleStr.includes('管理员');
    }
  },
  data() {
    return {
      version: "0.0.1",
      overview: {
        yesterdaySales: 0,
        pendingAuditOrders: 0
      }
    }
  },
  created() {
    if (this.isAdmin) {
      this.refreshOverview();
      this._overviewTimer = setInterval(this.refreshOverview, 60000);
    }
  },
  beforeDestroy() {
    if (this._overviewTimer) {
      clearInterval(this._overviewTimer);
    }
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    refreshOverview() {
      getOrderOverview().then(res => {
        const data = res.data || {};
        this.overview.yesterdaySales = data.yesterdaySales || 0;
        this.overview.pendingAuditOrders = data.pendingAuditOrders || 0;
      }).catch(() => {});
    }
  }
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;
    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  // 美观的欢迎区域样式
  .welcome-card {
    background: linear-gradient(120deg, #f5f7fa 0%, #e4eaf5 100%);
    border-radius: 12px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
    padding: 25px 20px;
    margin-top: 20px;
    border: 1px solid #e8ebf2;

    .welcome-title {
      font-size: 22px;
      color: #2c3e50;
      margin: 0 0 8px 0;
      font-weight: 600;
    }

    .welcome-desc {
      font-size: 14px;
      color: #667eea;
      margin: 0 0 10px 0;
      line-height: 1.5;
    }

    .admin-tips {
      .tips-item {
        background: #ffffff;
        border-radius: 8px;
        padding: 12px 10px;
        margin-bottom: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.03);

        .tips-label {
          font-size: 13px;
          color: #34495e;
          font-weight: 500;
        }

        .tips-value {
          font-size: 13px;
          color: #e74c3c;
          margin-left: 5px;
        }
      }
    }
  }
}
</style>