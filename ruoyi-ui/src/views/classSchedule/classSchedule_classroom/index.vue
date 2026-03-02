<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教室名称" prop="classroomName">
        <el-input
          v-model="queryParams.classroomName"
          placeholder="请输入教室名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属楼宇" prop="building">
        <el-input
          v-model="queryParams.building"
          placeholder="请输入所属楼宇"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所在楼层" prop="floor">
        <el-input
          v-model="queryParams.floor"
          placeholder="请输入所在楼层"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="教室容量" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入教室容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配备设备" prop="equipment">
        <el-input
          v-model="queryParams.equipment"
          placeholder="请输入配备设备"
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
          v-hasPermi="['classSchedule:classSchedule_classroom:add']"
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
          v-hasPermi="['classSchedule:classSchedule_classroom:edit']"
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
          v-hasPermi="['classSchedule:classSchedule_classroom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['classSchedule:classSchedule_classroom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="classSchedule_classroomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教室ID" align="center" prop="classroomId" />
      <el-table-column label="教室名称" align="center" prop="classroomName" />
      <el-table-column label="所属楼宇" align="center" prop="building" />
      <el-table-column label="所在楼层" align="center" prop="floor" />
      <el-table-column label="教室容量" align="center" prop="capacity" />
      <el-table-column label="教室类型" align="center" prop="classroomType" />
      <el-table-column label="配备设备" align="center" prop="equipment" />
      <el-table-column label="可用状态" align="center" prop="availableStatus" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_classroom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['classSchedule:classSchedule_classroom:remove']"
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

    <!-- 添加或修改教室资源对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教室名称" prop="classroomName">
          <el-input v-model="form.classroomName" placeholder="请输入教室名称" />
        </el-form-item>
        <el-form-item label="所属楼宇" prop="building">
          <el-input v-model="form.building" placeholder="请输入所属楼宇" />
        </el-form-item>
        <el-form-item label="所在楼层" prop="floor">
          <el-input v-model="form.floor" placeholder="请输入所在楼层" />
        </el-form-item>
        <el-form-item label="教室容量" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入教室容量" />
        </el-form-item>
        <el-form-item label="配备设备" prop="equipment">
          <el-input v-model="form.equipment" placeholder="请输入配备设备" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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
import { listClassSchedule_classroom, getClassSchedule_classroom, delClassSchedule_classroom, addClassSchedule_classroom, updateClassSchedule_classroom } from "@/api/classSchedule/classSchedule_classroom"

export default {
  name: "ClassSchedule_classroom",
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
      // 教室资源表格数据
      classSchedule_classroomList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classroomName: null,
        building: null,
        floor: null,
        capacity: null,
        classroomType: null,
        equipment: null,
        availableStatus: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        classroomName: [
          { required: true, message: "教室名称不能为空", trigger: "blur" }
        ],
        building: [
          { required: true, message: "所属楼宇不能为空", trigger: "blur" }
        ],
        floor: [
          { required: true, message: "所在楼层不能为空", trigger: "blur" }
        ],
        capacity: [
          { required: true, message: "教室容量不能为空", trigger: "blur" }
        ],
        classroomType: [
          { required: true, message: "教室类型不能为空", trigger: "change" }
        ],
        availableStatus: [
          { required: true, message: "可用状态不能为空", trigger: "change" }
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
    /** 查询教室资源列表 */
    getList() {
      this.loading = true
      listClassSchedule_classroom(this.queryParams).then(response => {
        this.classSchedule_classroomList = response.rows
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
        classroomId: null,
        classroomName: null,
        building: null,
        floor: null,
        capacity: null,
        classroomType: null,
        equipment: null,
        availableStatus: null,
        remark: null,
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
      this.ids = selection.map(item => item.classroomId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加教室资源"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const classroomId = row.classroomId || this.ids
      getClassSchedule_classroom(classroomId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改教室资源"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.classroomId != null) {
            updateClassSchedule_classroom(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addClassSchedule_classroom(this.form).then(response => {
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
      const classroomIds = row.classroomId || this.ids
      this.$modal.confirm('是否确认删除教室资源编号为"' + classroomIds + '"的数据项？').then(function() {
        return delClassSchedule_classroom(classroomIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('classSchedule/classSchedule_classroom/export', {
        ...this.queryParams
      }, `classSchedule_classroom_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
