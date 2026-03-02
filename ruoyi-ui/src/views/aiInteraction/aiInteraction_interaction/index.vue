<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户 ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交互时间" prop="interactionTime">
        <el-date-picker clearable
          v-model="queryParams.interactionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择交互时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否收藏" prop="isCollect">
        <el-input
          v-model="queryParams.isCollect"
          placeholder="请输入是否收藏"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文档解析时存储文档 URL" prop="documentUrl">
        <el-input
          v-model="queryParams.documentUrl"
          placeholder="请输入文档解析时存储文档 URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="图片识别时存储图片 URL" prop="imageUrl">
        <el-input
          v-model="queryParams.imageUrl"
          placeholder="请输入图片识别时存储图片 URL"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系统功能引导标识" prop="guideFunction">
        <el-input
          v-model="queryParams.guideFunction"
          placeholder="请输入系统功能引导标识"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="更新用户" prop="updateUserId">
        <el-input
          v-model="queryParams.updateUserId"
          placeholder="请输入更新用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="删除状态" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入删除状态"
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
          v-hasPermi="['aiInteraction:aiInteraction_interaction:add']"
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
          v-hasPermi="['aiInteraction:aiInteraction_interaction:edit']"
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
          v-hasPermi="['aiInteraction:aiInteraction_interaction:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['aiInteraction:aiInteraction_interaction:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aiInteraction_interactionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="交互 ID" align="center" prop="interactionId" />
      <el-table-column label="用户 ID" align="center" prop="userId" />
      <el-table-column label="交互类型" align="center" prop="interactionType" />
      <el-table-column label="交互状态" align="center" prop="interactionStatus" />
      <el-table-column label="请求内容" align="center" prop="requestContent" />
      <el-table-column label="响应内容" align="center" prop="responseContent" />
      <el-table-column label="交互时间" align="center" prop="interactionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.interactionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否收藏" align="center" prop="isCollect" />
      <el-table-column label="文档解析时存储文档 URL" align="center" prop="documentUrl" />
      <el-table-column label="图片识别时存储图片 URL" align="center" prop="imageUrl" />
      <el-table-column label="系统功能引导标识" align="center" prop="guideFunction" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['aiInteraction:aiInteraction_interaction:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['aiInteraction:aiInteraction_interaction:remove']"
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

    <!-- 添加或修改智能交互记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户 ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户 ID" />
        </el-form-item>
        <el-form-item label="请求内容">
          <editor v-model="form.requestContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="响应内容">
          <editor v-model="form.responseContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="交互时间" prop="interactionTime">
          <el-date-picker clearable
            v-model="form.interactionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择交互时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否收藏" prop="isCollect">
          <el-input v-model="form.isCollect" placeholder="请输入是否收藏" />
        </el-form-item>
        <el-form-item label="文档解析时存储文档 URL" prop="documentUrl">
          <el-input v-model="form.documentUrl" placeholder="请输入文档解析时存储文档 URL" />
        </el-form-item>
        <el-form-item label="图片识别时存储图片 URL" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入图片识别时存储图片 URL" />
        </el-form-item>
        <el-form-item label="系统功能引导标识" prop="guideFunction">
          <el-input v-model="form.guideFunction" placeholder="请输入系统功能引导标识" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
        </el-form-item>
        <el-form-item label="删除状态" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入删除状态" />
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
import { listAiInteraction_interaction, getAiInteraction_interaction, delAiInteraction_interaction, addAiInteraction_interaction, updateAiInteraction_interaction } from "@/api/aiInteraction/aiInteraction_interaction"

export default {
  name: "AiInteraction_interaction",
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
      // 智能交互记录表格数据
      aiInteraction_interactionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        interactionType: null,
        interactionStatus: null,
        requestContent: null,
        responseContent: null,
        interactionTime: null,
        isCollect: null,
        documentUrl: null,
        imageUrl: null,
        guideFunction: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户 ID不能为空", trigger: "blur" }
        ],
        interactionType: [
          { required: true, message: "交互类型不能为空", trigger: "change" }
        ],
        interactionStatus: [
          { required: true, message: "交互状态不能为空", trigger: "change" }
        ],
        requestContent: [
          { required: true, message: "请求内容不能为空", trigger: "blur" }
        ],
        interactionTime: [
          { required: true, message: "交互时间不能为空", trigger: "blur" }
        ],
        isCollect: [
          { required: true, message: "是否收藏不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "更新时间不能为空", trigger: "blur" }
        ],
        updateUserId: [
          { required: true, message: "更新用户不能为空", trigger: "blur" }
        ],
        isDeleted: [
          { required: true, message: "删除状态不能为空", trigger: "blur" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询智能交互记录列表 */
    getList() {
      this.loading = true
      listAiInteraction_interaction(this.queryParams).then(response => {
        this.aiInteraction_interactionList = response.rows
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
        interactionId: null,
        userId: null,
        interactionType: null,
        interactionStatus: null,
        requestContent: null,
        responseContent: null,
        interactionTime: null,
        isCollect: null,
        documentUrl: null,
        imageUrl: null,
        guideFunction: null,
        createTime: null,
        updateTime: null,
        updateUserId: null,
        isDeleted: null
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
      this.ids = selection.map(item => item.interactionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加智能交互记录"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const interactionId = row.interactionId || this.ids
      getAiInteraction_interaction(interactionId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改智能交互记录"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.interactionId != null) {
            updateAiInteraction_interaction(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAiInteraction_interaction(this.form).then(response => {
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
      const interactionIds = row.interactionId || this.ids
      this.$modal.confirm('是否确认删除智能交互记录编号为"' + interactionIds + '"的数据项？').then(function() {
        return delAiInteraction_interaction(interactionIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('aiInteraction/aiInteraction_interaction/export', {
        ...this.queryParams
      }, `aiInteraction_interaction_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
