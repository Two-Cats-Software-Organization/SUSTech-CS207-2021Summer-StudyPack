package model.record;

import util.ResourcesManager;

import java.io.*;

/**@author 叶璨铭
 * @version 2.0
 * 类名：存档控制器，本class不是工具包，而是面向对象的一个真实控制器的类型
 * 0.注意！！！ArchiveManager不是存档本身，存档实际上是回合控制器
 * 因此构造一个ArchiveManager只是得到了控制某个存档的方法，并不是已经加载了这个存档！！
 *
 * 1.存档是java对象存档，不采用额外的加密
 * 2.反作弊机制指的仅仅是检测数据是否可以构成一个完整、可加载游戏
 * 3.本工具包包括存档编辑器、存档和读档器等。
 */
public class ArchiveManager implements Serializable{
    public static String defaultArchivesPath = ResourcesManager.getInstance().CalculateProjectPath()+"data/";//一个可以调用的常量，仅供参考
    private static ArchiveManager instance = new ArchiveManager();
    public static ArchiveManager getInstance() {
        return instance;
    }

    private ArchiveManager(){
        this(defaultArchivesPath);
    }
    private String path_Archive_file;//一个实例存档具有的位置信息
    @Deprecated //应该 使用单例模式
    public ArchiveManager(String path_Archive_file) {
        this.path_Archive_file = path_Archive_file;
    }

    /**
     * @什么时候用： only after the start window call 回合控制器，
     * and 回合控制器try to
     * 解析存档（仅第一回合），can this method be called.
     * @怎么用 把回合控制器传进来，当这个函数结束的时候，你的回合控制器
     * 已经自动获得了各种参数（从path文件中）
//     * @param oldRoundController 回合控制器
//     * @param path 存档文件的相对于项目的路径或者绝对路径
     */
//    public Record load(){
//        try (//with resources
//             ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.path_Archive_file))
//        ){
//            return (Record) in.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    public void load(Record oldRecord){
//        load(oldRecord, this.path_Archive_file);
//    }
    public Record load( final String name){
        try (//with resources
             ObjectInputStream in = new ObjectInputStream(new FileInputStream(path_Archive_file+name.replaceAll("\\s+","")+".dat"))
        ){
            return  (Record) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @什么时候用： 回合结束阶段，自动保存游戏进度
     * @怎么用 把回合控制器传进来，当这个函数结束的时候，你的回合控制器
     * 已经自动导出了各种参数（到path文件中）
     * @param record 回合控制器
//     * @param path 存档文件的相对于项目的路径或者绝对路径
     */
//    public void save(final Record record){
//        save(record, this.path_Archive_file);
//    }
    public void save(final Record record, final String name){
        try (//with resources
             ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path_Archive_file+name.replaceAll("\\s+","")+".dat"))
        ){
            out.writeObject(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new FileOutputStream(ArchiveManager.getInstance().path_Archive_file+"1234"));
    }

    //作为工具类
    /**
     *
     * @return 默认存档路径下的存档列表.
     */
    public static String[] ListArchives(String folder_path){
        File folder = new File(folder_path);
        return ListArchives(folder);
    }
    private static String[] ListArchives(File folder) {
        return folder.list();
    }
}