1、
依赖此module的主项目，需要在build.gradle中添加
repositories{
    flatDir{
        dirs 'libs', '../Common.map.baiduguide/libs'
    }
}

2、Common.map.baiduguide\src\main\java\com\baidu\android\bbalbs\common
这个包内的东西是百度一个jar包里面的，由于和百度的主包冲突，所以拿出来手动补全和主包不一样的地方





