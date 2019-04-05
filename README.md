# ANavigator
这是一个优化startActivityForResult业务逻辑的轻量级框架。

# for what?
平常用startActivityForResult来启动一个新页面，并且从新页面返回的时候需要根据结果处理相应逻辑。如：
``` java
// ListTodo
public class ActivityListTodo extends Activity{
  private void launchTodoEdit(int id) {
    Intent intent = new Intent(ActivityListTodo.this, ActivityEditTodo.class);
    startActivityForResult(intent, 1);
  }
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
      // do something
    }
  }
}
```
诸如上面的场景很多，大多做法如此，因为Android SDK也没有提供其他更好的方式。但是，这种方式却让业务逻辑变得很零散。那么能不能像下面一样组织代码：
``` java
ANavigator.getInstance()
  .from(ActivityListTodo.this)
  .to(new Intent(ActivityListTodo.this, ActivityListTodo.class))
  .onResult(new ResultConsumer() {
     @Override
     public void onResult(int resultCode, @Nullable Intent dataResult) {
       if (resultCode == RESULT_OK) {
         // do something
       }
     }
  })
  .apply();
```
好了，使用方式我也讲完了，就这么多。
