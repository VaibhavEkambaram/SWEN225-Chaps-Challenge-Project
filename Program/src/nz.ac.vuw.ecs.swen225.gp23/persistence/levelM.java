package nz.ac.vuw.ecs.swen225.gp23.persistence;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class levelM {
    private static ArrayList<String> lDesc = new ArrayList<>();
    public static int currentLevel = 0;
    @SuppressWarnings("rawtypes")
    static Set<Class> cSet = new HashSet<>();



    public static void load(assetManager aManager){
        File[] folder = new File("src/levels/").listFiles();
        List<File> files = new ArrayList<>();
        if(folder == null){
            return;
        }
        for(File f : folder){
            files.add(f);
        }

        JsonParser jsonParser = Json.createParser(new StringReader("[]"));


        Collections.sort(files);
        for(File f : files){
            try{
                try(ZipFile zipFile = new ZipFile(f.getAbsolutePath())){
                    zipFile.stream().filter(l -> l.getName().contains(".txt")).forEach(b -> {
                        try{
                            lDesc.add(new BufferedReader(new InputStreamReader(zipFile.getInputStream(b))).readLine());
                        } catch (IOException e) {
                            throw new Error("Failed to load");
                        }
                    });
                zipFile.stream().filter(b -> b.getName().contains(".png")).forEach(l -> {
                    try{
                        aManager.loadAssetInputStream(zipFile.getInputStream(l), l.getName());
                    } catch (IOException e) {
                        System.out.println("Error loading asset" + e);
                    }
                });
                }

        } catch (Exception e){
                System.out.println("Error when opening Zip:" + e);
            }
        }

        try{
            folder = new File("src/levels/").listFiles();
            files.clear();
            for(File f : files){
                files.add(f);
            }
            for(File f : files){
                JarFile jR = new JarFile(f);
                Enumeration<JarEntry> entry = jR.entries();

                URL[] urls = {new URL("jar:file:" + jR.getName() + "!/")};
                URLClassLoader classLoader = URLClassLoader.newInstance(urls);

                while(entry.hasMoreElements()){
                    JarEntry jE = entry.nextElement();
                    if(jE.isDirectory() || !jE.getName().endsWith(".class")){
                        continue;
                    }
                    String classN = jE.getName().substring(0, jE.getName().length() - 6);
                    classN = classN.replace('/', '.');
                    Class<?> c = classLoader.loadClass(classN);
                    cSet.add(c);
                }
                jR.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading classes:" + e);

        }
    }

    public static int getIntOfCurrentLevel(){
        return currentLevel;
    }

}
