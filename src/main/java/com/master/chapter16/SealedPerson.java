package com.master.chapter16;

/**
 * @Description: java类的密封性
 * @Author: ColorXJH
 * @CreateDate: 2023/10/27 23:03
 * @Version: 1.0
 */
//密封的类
public sealed class SealedPerson permits Teachers, Students, Workers {
}
//最终的类
final class Teachers extends SealedPerson {
}
//密封的类
sealed class Students extends SealedPerson permits HighSchoolStudent, MiddleSchoolStudent {
}
//最终的类
final class MiddleSchoolStudent extends Students {
}
//最终的类
final class HighSchoolStudent extends Students {
}
//非密封的类
non-sealed class Workers extends SealedPerson {
}
//很普通的子类
class RailWayWorker extends Workers{}

/**
 * 声明为sealed的类，通过permits声明其子类
 * 子类必须使用final\sealed\non-sealed修饰符修饰
 */
