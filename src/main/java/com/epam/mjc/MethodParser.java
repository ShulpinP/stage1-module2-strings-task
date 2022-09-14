package com.epam.mjc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] splitString = signatureString.split("\\(");
        int lenghtOfArguments = splitString[1].length()-1;
        String argumentsInSignature = splitString[1].substring(0, lenghtOfArguments);
        String partsInSignature = splitString[0];
        Map<String,String> signatureMap = parseParts(partsInSignature);
        List<MethodSignature.Argument> arguments = parseArguments(argumentsInSignature);
        MethodSignature methodSignature = new MethodSignature(signatureMap.get("methodName"),arguments);
        methodSignature.setReturnType(signatureMap.get("returnType"));
        methodSignature.setAccessModifier(signatureMap.get("accessModifier"));
        return methodSignature;
    }

     private List<MethodSignature.Argument> parseArguments (String argumentsInSignature) {
         List<MethodSignature.Argument> arguments = new ArrayList<>();
         if (!argumentsInSignature.isEmpty()) {
             String[] argumentsList = argumentsInSignature.split(", ");
             for (String s : argumentsList) {
                 String[] argumentParameters = s.split(" ");
                 arguments.add(new MethodSignature.Argument(argumentParameters[0],argumentParameters[1]));
             }
         }
             return arguments;
         }
        private Map<String,String> parseParts(String partsOfSignature) {
            Map<String,String> partsMap = new HashMap<>();
            String[] partsList = partsOfSignature.split(" ");
            int flag = 0;
            if (partsList.length == 3) {
                partsMap.put("accessModifier", partsList[flag++]);
            }
            partsMap.put("returnType",partsList[flag++]);
            partsMap.put("methodName",partsList[flag]);
            return partsMap;
            }
    }

