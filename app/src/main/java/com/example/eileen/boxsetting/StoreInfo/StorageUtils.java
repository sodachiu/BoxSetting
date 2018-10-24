package com.example.eileen.boxsetting.StoreInfo;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

public class StorageUtils {

    public static boolean fileIsExists(String path) {
        try {
            File f = new File(path);
            StorageLog.LOGI("f path >>>" + f.getAbsolutePath());
            if (!f.exists()) {
                StorageLog.LOGI("文件夹不存在" );

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获得机身内部存储总大小
     * 总ROM 获取到的是4.20GB
     *
     * @return
     */
    public static long getRomTotalSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return blockSize * totalBlocks;
    }

    /**
     * 获得内存存储可用空间
     * ROM
     *
     * @return
     */
    public static long getRomAvailableSize() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return blockSize * availableBlocks;
        //return Formatter.formatFileSize(mContext, freeSize);
    }

    /**
     * 获得外部存储总大小
     *
     * @return
     */
    public static long getExternalTotalSize(String strpath) {
        String path = strpath;
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        return blockSize * totalBlocks;
    }

    /**
     * 获得外部存储可用空间
     * U盘
     *
     * @return
     */
    public static long getExternalAvailableSize(String strpath) {
        String path = strpath;
        StatFs stat = new StatFs(path);
        long blockSize = stat.getBlockSizeLong();
        long availableBlocks = stat.getAvailableBlocksLong();
        return blockSize * availableBlocks;
    }

}
