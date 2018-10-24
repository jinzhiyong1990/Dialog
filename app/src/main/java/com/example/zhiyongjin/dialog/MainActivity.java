package com.example.zhiyongjin.dialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button);

    }

    public void myclick(View view) {
        switch (view.getId()) {

            case R.id.putong1:
                showNormalDialog();
                break;

            case R.id.putong2:
                showNormalDialog2();
                break;

            case R.id.btn_list:
                showListDialog();
                break;

            case R.id.btn_single:
                showSingleDialog();
                break;
            case R.id.btn_multi:
                showMultiDialog();
                break;

            case R.id.btn_progress:
                showProgress_WaitDialog();
                break;

            case R.id.btn_progree_jindutiao:
                showProgress_jindutiao();
                break;

            case R.id.btn_input:
                showInputDialog();
                break;

            case R.id.btn_self:
//                1. 自定义一个类, 继承于Dialog, 在构造方法中调用setContentView(R.layout.xx)
//                来设定对话框的布局

//                2. 设定自定义对话框的风格(不显示标题栏, 不显示背景){需要去style.xml去设置一下}

//                3. 调用含设定对话框风格参数的构造
//                4. 对自定义对话框中的某些控件添加事件



//                实例化自定义的对话框, 显示
                MyDialog dialog = new MyDialog(this);
                dialog.show();
break;

            case R.id.btn_array:
                showArrayDialog();
break;
        }
    }

//    案例: ArrayAdapter
    private void showArrayDialog() {
        String[] items = {"Java", "Python", "C", "C++", "PHP", "HTML"};
//        数组适配器
//        参数1: 环境
//        参数2: 布局资源索引, 指的是每一项数据所呈现的样式android:R.layout.xx
//        参数3: 数据源
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,items);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")

//                参数1: 适配器对象(对数据显示样式的规则制定器)
//                参数2: 监听器
                .setAdapter(adapter,null);
        builder.show();


    }

    private void showInputDialog() {
        final EditText editText = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您输入的是: " +
                                editText.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();


    }

    //进度条对话框
    private void showProgress_jindutiao() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("下载中");
        dialog.setMessage("请等待");
//        下面这个默认就是false的!
//        true: 设置进度条进度模糊, 不管它走到哪了
        dialog.setIndeterminate(false);
        //        设置对话框样式为水平样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
        new Thread() {
            public void run() {
                super.run();
                for (int i = 0; i <= 100; i++) {
                    dialog.setProgress(i);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }
        }.start();

    }

    //    等待对话框
    private void showProgress_WaitDialog() {
        ProgressDialog dialog2 = new ProgressDialog(this);
        dialog2.setTitle("我是一个等待对话框");
        dialog2.setMessage("请等待");
        dialog2.setCancelable(false); //默认是true, 如果是false的话, 那么点击不会消失
        dialog2.show();
//        dialog.dismiss(); //设置对话框消失, 不会显示出来对话框了!

    }

    //    多选对话框
    private void showMultiDialog() {
        final String[] sports = {"篮球", "足球", "网球", "乒乓球", "游泳"};
        final boolean[] checked = {true, false, true, true, false, false};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择你最喜欢的运动")
//                参数1: 选项
//                参数2: 默认备选项(true:选中, false:未选中)
//                参数3: 被点击时会触发的事件
                .setMultiChoiceItems(sports, checked, new DialogInterface.OnMultiChoiceClickListener() {
                    //                    参数1：对话框本身
//                    参数2： 按钮的索引
//                    参数3： 标志按钮是否处于被选中true或者false
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                        无论选中还是取消都会触发onclick方法
//                        保存选中状态的方法：
                        Log.e("LOG", sports[which]);
//                        下面这个有没有都一样， 因为系统直接帮你做了
//                        checked[which] = isChecked;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String msg = "您的爱好是: ";
                        for (int i = 0; i < checked.length; i++) {
                            if (checked[i]) {
                                msg += sports[i] + " ";
                            }
                        }
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();
    }

    //    单选对话框
    int idx = 0;

    private void showSingleDialog() {
        final String[] stars = {"奥黛丽赫本", "安妮海瑟薇", "卧槽", "这是谁"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择你喜欢的明星")
//                参数1:选项
//                参数2:默认被选项, 传选项的索引
//                参数3:被选中的事件
                .setSingleChoiceItems(stars, 1, new DialogInterface.OnClickListener() {
                    @Override
//                    因为需要确定之后, 再显示最后的结果, 所以, 这个里面的索引, 要传入到下面确定里面
//                    idx是一个全局参数
                    public void onClick(DialogInterface dialog, int which) {
                        idx = which;
                    }
//                    对于单选按钮, 点完了之后不会自动消失, 所以需要有一个确定的按钮
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您最喜欢的明星是:" + stars[idx], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();

    }

    //列表对话框
    private void showListDialog() {
        final String[] item = {"我是1", "我是2", "我是3", "我是4"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("请选择")
//                决定列表长什么样
//                设置列表项
                .setItems(item, new DialogInterface.OnClickListener() {
                    @Override

//                    里面int which, 代表的是list里面的索引, 被点击项的索引
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "您选择了: " + item[which], Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();


    }

    //    普通对话框1
    public void showNormalDialog() {
//         Alertdialog
//        Alertdialog的构造方法是被修饰为protected
//        因此包外是无法使用的, 所以我们利用构建器(Builder)

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

//        设置对话框的标题
        builder.setTitle("提示");

//        设置内容
        builder.setMessage("你是否确定退出当前程序");
//        设置按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                退出当前程序
                MainActivity.this.finish();
            }
        });
        builder.setNegativeButton("取消", null);
//        builder.setNeutralButton();


//        对话框的显示方法
        builder.show();
    }

    //    普通对话框2
    public void showNormalDialog2() {
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("提示");
        dialog.setMessage("请为本次课堂打分");
//        5 3 1
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "5份", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您选择了:5分", Toast.LENGTH_SHORT).show();
            }
        });

//        不想有信息, 不能写null, 需要调用OnClickListener, 但是里面不写东西
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "3分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "1分", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

//        一定要调用show方法, 否则对话框不展示
        dialog.show();


    }
}
