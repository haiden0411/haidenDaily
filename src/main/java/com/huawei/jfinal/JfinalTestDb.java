package com.huawei.jfinal;

import com.huawei.springboot.domain.User;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;

import java.util.List;

/**
 * Author：胡灯
 * Date：2018-11-07 22:30
 * Description：<描述>
 */
public class JfinalTestDb
{
    public static void main(String[] args) {
        PropKit.use("config.properties");
        DruidPlugin dp = new DruidPlugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.addMapping("user", User.class);
        arp.setShowSql(true);
        dp.start();
        arp.start();
        System.out.println(User.dao.find("select * from user").size());


       /* for (int i = 0; i < 50; i++) {
            //User.dao.set("name","merry"+i).set("age",20+i).set("remark","he man ok"+i).set("sex",1).save();
            Record user = new Record().set("name","merry"+i).set("age",20+i).set("remark","he man ok"+i).set("sex",1);
            Db.save("user",user);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        //User.dao.find("select * from user t where t.id = 6").get(0).delete();
        //User.dao.deleteById(7);
        System.out.println(User.dao.findById(8).get("name").toString());
        User user = User.dao.findByIdLoadColumns("8", "name,age");
        System.out.println(user.get("name").toString());
        Integer age = user.get("age");
        System.out.println(age);
        System.out.println("分布查询");

        Page<Record> userPage = Db.paginate(1, 10, "select * ", "from user where age > ?", 30);
        List<Record> list = userPage.getList();
        for(Record record:list){

            System.out.println(record.get("name").toString());
        }

        List<Record> records = Db.find("select * from user where age > 20");
        System.out.println(records.size());



    }
}
