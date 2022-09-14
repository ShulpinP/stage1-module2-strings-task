package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

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
        MethodSignature methodSignature = null;
        ArrayList<MethodSignature.Argument> argumentList = new ArrayList<>();

        String argumentsInSignature = signatureString.substring(signatureString.indexOf("("),signatureString.indexOf(")"));
        if (!argumentsInSignature.equals("")) {
        String[] argumentsList = argumentsInSignature.split(", ");
        MethodSignature.Argument argument = null;
        for (String s : argumentsList) {
            String[] argumentParameters = s.split(" ");
            argument.setType(argumentParameters[0]);
            argument.setName(argumentParameters[1]);
            argumentList.add(argument);
        }
        }
        String partsOfSignature = signatureString.substring(0,signatureString.indexOf("("));
        List<String> partsList = new ArrayList<>(List.of(partsOfSignature.split(" ")));
        if (partsList.size() == 2) {
            partsList.add(0, "");
        }
            methodSignature.setAccessModifier(partsList.get(0));
            methodSignature.setReturnType(partsList.get(1));
            methodSignature.setMethodName(partsList.get(2));
        return methodSignature;

        }

    }

