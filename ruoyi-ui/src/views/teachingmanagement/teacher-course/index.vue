<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师 ID" prop="teacherId">
        <el-input
          v-model="queryParams.teacherId"
          placeholder="请输入教师 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="课程 ID" prop="courseId">
        <el-input
          v-model="queryParams.courseId"
          placeholder="请输入课程 ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学期" prop="semester">
        <el-input
          v-model="queryParams.semester"
          placeholder="请输入学期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上课时间" prop="classTime">
        <el-input
          v-model="queryParams.classTime"
          placeholder="请输入上课时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上课教室" prop="classRoom">
        <el-input
          v-model="queryParams.classRoom"
          placeholder="请输入上课教室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选课人数限制" prop="studentLimit">
        <el-input
          v-model="queryParams.studentLimit"
          placeholder="请输入选课人数限制"
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
          v-hasPermi="['teachingmanagement:teacher-course:add']"
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
          v-hasPermi="['teachingmanagement:teacher-course:edit']"
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
          v-hasPermi="['teachingmanagement:teacher-course:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['teachingmanagement:teacher-course:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherCourseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="关联记录 ID" align="center" prop="tcId" />
      <el-table-column label="教师 ID" align="center" prop="teacherId" />
      <el-table-column label="课程 ID" align="center" prop="courseId" />
      <el-table-column label="学期" align="center" prop="semester" />
      <el-table-column label="上课时间" align="center" prop="classTime" />
      <el-table-column label="上课教室" align="center" prop="classRoom" />
      <el-table-column label="选课人数限制" align="center" prop="studentLimit" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="更新用户" align="center" prop="updateUserId" />
      <el-table-column label="删除状态" align="center" prop="isDeleted" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['teachingmanagement:teacher-course:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['teachingmanagement:teacher-course:remove']"
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

    <!-- 添加或修改教师课程关联对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="教师 ID" prop="teacherId">
          <el-input v-model="form.teacherId" placeholder="请输入教师 ID" />
        </el-form-item>
        <el-form-item label="课程 ID" prop="courseId">
          <el-input v-model="form.courseId" placeholder="请输入课程 ID" />
        </el-form-item>
        <el-form-item label="学期" prop="semester">
          <el-input v-model="form.semester" placeholder="请输入学期" />
        </el-form-item>
        <el-form-item label="上课时间" prop="classTime">
          <el-input v-model="form.classTime" placeholder="请输入上课时间" />
        </el-form-item>
        <el-form-item label="上课教室" prop="classRoom">
          <el-input v-model="form.classRoom" placeholder="请输入上课教室" />
        </el-form-item>
        <el-form-item label="选课人数限制" prop="studentLimit">
          <el-input v-model="form.studentLimit" placeholder="请输入选课人数限制" />
        </el-form-item>
        <el-form-item label="更新用户" prop="updateUserId">
          <el-input v-model="form.updateUserId" placeholder="请输入更新用户" />
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
import { listTeacherCourse, getTeacherCourse, delTeacherCourse, addTeacherCourse, updateTeacherCourse } from "@/api/teachingmanagement/teacher-course"

export default {
  name: "TeacherCourse",
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
      // 教师课程关联表格数据
      teacherCourseList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherId: null,
        courseId: null,
        semester: null,
        classTime: null,
        classRoom: null,
        studentLimit: null,
        status: null,
        updateUserId: null,
        isDeleted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teacherId: [
          { required: true, message: "教师 ID不能为空", trigger: "blur" }
        ],
        courseId: [
          { required: true, message: "课程 ID不能为空", trigger: "blur" }
        ],
        semester: [
          { required: true, message: "学期不能为空", trigger: "blur" }
        ],
        studentLimit: [
          { required: true, message: "选课人数限制不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态不能为空", trigger: "change" }
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
          { required: true, message: "删除状态不能为空", trigger: "change" }
        ]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 查询教师课程关联列表 */
    getList() {
      this.loading = true
      listTeacherCourse(this.queryParams).then(response => {
        this.teacherCourseList = response.rows
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
        tcId: null,
        teacherId: null,
        courseId: null,
        semester: null,
        classTime: null,
        classRoom: null,
        studentLimit: null,
        status: null,
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
      this.ids = selection.map(item => item.tcId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "添加教师课程关联"
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const tcId = row.tcId || this.ids
      getTeacherCourse(tcId).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改教师课程关联"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tcId != null) {
            updateTeacherCourse(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTeacherCourse(this.form).then(response => {
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
      const tcIds = row.tcId || this.ids
      this.$modal.confirm('是否确认删除教师课程关联编号为"' + tcIds + '"的数据项？').then(function() {
        return delTeacherCourse(tcIds)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('teachingmanagement/teacher-course/export', {
        ...this.queryParams
      }, `teacher-course_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
