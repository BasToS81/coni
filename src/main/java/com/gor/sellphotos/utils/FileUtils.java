package com.gor.sellphotos.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static void zip(String targetFolderPath, String destinationFilePath, String password) {
        try {
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);

            if (password.length() > 0) {
                parameters.setEncryptFiles(true);
                parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
                parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);

                parameters.setPassword(password);
            }

            ZipFile zipFile = new ZipFile(destinationFilePath);

            File targetFile = new File(targetFolderPath);
            if (targetFile.isFile()) {
                zipFile.addFile(targetFile, parameters);
            }
            else if (targetFile.isDirectory()) {
                zipFile.addFolder(targetFile, parameters);
            }
            else {
                // show error message
            }

        }
        catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public static void unzip(String targetZipFilePath, String destinationFolderPath, String password) {
        try {
            ZipFile zipFile = new ZipFile(targetZipFilePath);
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password);
            }
            zipFile.extractAll(destinationFolderPath);
        }
        catch (ZipException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File children : dir.listFiles()) {
                boolean success = deleteDirectory(children);
                if (!success)
                    return false;
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    public static void zipDir(String dirName, String nameZipFile) throws IOException {
        ZipOutputStream zip = null;
        FileOutputStream fW = null;
        fW = new FileOutputStream(nameZipFile);
        zip = new ZipOutputStream(fW);
        addFolderToZip("", dirName, zip);
        zip.close();
        fW.close();
    }

    private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws IOException {
        File folder = new File(srcFolder);
        if (folder.list().length == 0) {
            addFileToZip(path, srcFolder, zip, true);
        }
        else {
            for (String fileName : folder.list()) {
                if (path.equals("")) {
                    addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip, false);
                }
                else {
                    addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip, false);
                }
            }
        }
    }

    private static void addFileToZip(String path, String srcFile, ZipOutputStream zip, boolean flag) throws IOException {
        File folder = new File(srcFile);
        if (flag) {
            zip.putNextEntry(new ZipEntry(path + "/" + folder.getName() + "/"));
        }
        else {
            if (folder.isDirectory()) {
                addFolderToZip(path, srcFile, zip);
            }
            else {
                byte[] buf = new byte[1024];
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
                while ((len = in.read(buf)) > 0) {
                    zip.write(buf, 0, len);
                }
                in.close();
            }
        }
    }
}
