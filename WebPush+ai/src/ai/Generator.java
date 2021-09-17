package ai;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Generator {
    private String title;
    private List<String> types = new ArrayList<>();
    private List<String> columns = new ArrayList<>();
    private List<List> dataList = new ArrayList();


    public void setTitle(String title) {
        this.title = title;
    }
    public void addMetaData(String column, String type) {
        columns.add(column);
        types.add(type);
    }
    public void addRow(List data)
    {
        dataList.add(data);
    }

    public String getARFFFormat() {
        String content = "@relation " + title + "\n\n";
        for (int i = 0; i != columns.size(); i++) {
            content += "@attribute " + columns.get(i) + " " + types.get(i) + "\n";
        }
        content += "\n@data \n\n";
        for (List row : dataList) {
            for (Object column : row) {
                content += column + ",";
            }
            content = content.substring(0, content.lastIndexOf(","))+"\n";
        }
        return content;
    }

    public String exportARFFTempFile() throws IOException {
        String fileName = System.currentTimeMillis() +"-"+ UUID.randomUUID()+ ".arff";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getARFFFormat());
        }
        file.deleteOnExit();
        return fileName;
    }

    public void exportARFFTempFile(String address) throws IOException {
        String fileName = address;
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getARFFFormat());
        }
        file.deleteOnExit();
    }

    public String exportARFFFile() throws IOException {
        String fileName = System.currentTimeMillis() +"-"+ UUID.randomUUID()+ ".arff";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getARFFFormat());
        }
        return fileName;
    }

    public void exportARFFFile(String address) throws IOException {
        String fileName = address;
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(getARFFFormat());
        }

    }
}
