# shop
学生项目
为了方便大家都能跑起来这个项目见证共同的成果和方便开发先做如下简单的教程(我是参考我现在的环境，具体将不断更新...)

    环境 IDEA (JDK最好安装jdk8)

    数据库配置mysql（Server version: 5.6.19-0ubuntu0.14.04.1 (Ubuntu)

    root用户登陆myql：

    注：依次导入shop-web下src/main/schema/下database-setup，database.sql(将自动创建相关的账户和导入相关的数据)或
    者在idea里面配置数据库就可以直接右键运行了

    关于相关的jar包管理

    在idea菜单栏上点击“view”---->Tool Buttons选项idea最右侧就会出现包含maven project的视图

    安装maven（记住maven的安装Home），在maven project视图下，查看相关的依赖是否正确

    有可能有一个依赖没有，我将它放在teambition上名字是“com.springsource.org.apache.commons.lang”依次放在maven
    的本地仓库相关的文件夹
    
------------------------------------------运行----------------------------------------------------------------

保证代码最新到本地 运行idea打开shop项目，此时idea就会不断载入配置(或者点击Maven project视图上的更新按钮)...

点菜单file-->project structure 设置sdk

之后点击最右侧Maven project之后就会出现maven管理的界面.

点maven project视图下的toolbar 下的更新按钮

点击shop（root）下面的lifecycle的clean，install（clean就是将原来模块依赖和编译好的文件删除，install就是编译安装
模块依赖到本地仓库和再次编译所有代码）

最后看所有模块都没有红线（有红线就说明是依赖没有配置好）

最后：选择shop-web下面的plugins下jetty下run就能跑起来了

    


