package ai;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedReader;
import java.io.FileReader;

public class Bayes {

    public String process(String dataSet, String query) throws Exception {
        //region File Reading
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dataSet));
        Instances train = new Instances(bufferedReader);
        train.setClassIndex(train.numAttributes() - 1);//where is ? column == X9

        bufferedReader = new BufferedReader(new FileReader(query));
        Instances test = new Instances(bufferedReader);
        test.setClassIndex(test.numAttributes() - 1);//where is ? column == X9


        FilteredClassifier classifier = new FilteredClassifier();
        classifier.setFilter(new StringToWordVector());
        classifier.setClassifier(new NaiveBayes());
        classifier.buildClassifier(train);

        Instances labeled = new Instances(test);

        String finalResult = "";

        for (int i = 0; i < test.numInstances(); i++) {//step is for each row in query
            double clsLabel = classifier.classifyInstance(test.instance(i));
            labeled.instance(i).setClassValue(clsLabel);
            finalResult = labeled.instance(i).attribute(1).value((int) clsLabel);
        }

        return finalResult;

    }

}
