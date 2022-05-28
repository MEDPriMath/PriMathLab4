package ru.itmo.markdown.description;

public class DescriptionStorage {
    public static String htmlPre = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "    <head>\n" +
            "        <meta charset=\"utf-8\" />\n" +
            "        <title>HTML Preview(markdown to html) - Editor.md examples</title>\n" +
            "        <link rel=\"stylesheet\" href=\"editor/examples/css/style.css\" />\n" +
            "        <link rel=\"stylesheet\" href=\"editor/css/editormd.preview.css\" />\n" +
            "        <style>            \n" +
            "            .editormd-html-preview {\n" +
            "                width: 90%;\n" +
            "                margin: 0 auto;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </head>\n" +
            "    <body>\n" +
            "        <div id=\"layout\">\n" +
            "            <div id=\"test-editormd-view2\">\n" +
            "                <textarea id=\"append-test\" style=\"display:none;\">";
    public static String htmlPost = "                </textarea>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <!-- <script src=\"js/zepto.min.js\"></script>\n" +
            "\t\t<script>\t\t\n" +
            "\t\t\tvar jQuery = Zepto;  // 为了避免修改flowChart.js和sequence-diagram.js的源码，所以使用Zepto.js时想支持flowChart/sequenceDiagram就得加上这一句\n" +
            "\t\t</script> -->\n" +
            "        <script src=\"editor/examples/js/jquery.min.js\"></script>\n" +
            "        <script src=\"editor/lib/marked.min.js\"></script>\n" +
            "        <script src=\"editor/lib/prettify.min.js\"></script>\n" +
            "        \n" +
            "        <script src=\"editor/lib/raphael.min.js\"></script>\n" +
            "        <script src=\"editor/lib/underscore.min.js\"></script>\n" +
            "        <script src=\"editor/lib/sequence-diagram.min.js\"></script>\n" +
            "        <script src=\"editor/lib/flowchart.min.js\"></script>\n" +
            "        <script src=\"editor/lib/jquery.flowchart.min.js\"></script>\n" +
            "\n" +
            "        <script src=\"editor/editormd.js\"></script>\n" +
            "        <script type=\"text/javascript\">\n" +
            "            $(function() {\n" +
            "                var testEditormdView, testEditormdView2;\n" +
            "                \n" +
            "                $.get(\"test.md\", function(markdown) {\n" +
            "                    \n" +
            "\t\t\t\t    testEditormdView = editormd.markdownToHTML(\"test-editormd-view\", {\n" +
            "                        markdown        : markdown ,//+ \"\\r\\n\" + $(\"#append-test\").text(),\n" +
            "                        //htmlDecode      : true,       // 开启 HTML 标签解析，为了安全性，默认不开启\n" +
            "                        htmlDecode      : \"style,script,iframe\",  // you can filter tags decode\n" +
            "                        //toc             : false,\n" +
            "                        tocm            : true,    // Using [TOCM]\n" +
            "                        //tocContainer    : \"#custom-toc-container\", // 自定义 ToC 容器层\n" +
            "                        //gfm             : false,\n" +
            "                        //tocDropdown     : true,\n" +
            "                        // markdownSourceCode : true, // 是否保留 Markdown 源码，即是否删除保存源码的 Textarea 标签\n" +
            "                        emoji           : true,\n" +
            "                        taskList        : true,\n" +
            "                        tex             : true,  // 默认不解析\n" +
            "                        flowChart       : true,  // 默认不解析\n" +
            "                        sequenceDiagram : true,  // 默认不解析\n" +
            "                    });\n" +
            "                    \n" +
            "                    //console.log(\"返回一个 jQuery 实例 =>\", testEditormdView);\n" +
            "                    \n" +
            "                    // 获取Markdown源码\n" +
            "                    //console.log(testEditormdView.getMarkdown());\n" +
            "                    \n" +
            "                    //alert(testEditormdView.getMarkdown());\n" +
            "                });\n" +
            "                    \n" +
            "                testEditormdView2 = editormd.markdownToHTML(\"test-editormd-view2\", {\n" +
            "                    htmlDecode      : \"style,script,iframe\",  // you can filter tags decode\n" +
            "                    emoji           : true,\n" +
            "                    taskList        : true,\n" +
            "                    tex             : true,  // 默认不解析\n" +
            "                    flowChart       : true,  // 默认不解析\n" +
            "                    sequenceDiagram : true,  // 默认不解析\n" +
            "                });\n" +
            "            });\n" +
            "        </script>\n" +
            "    </body>\n" +
            "</html>";

    public static String jacobiRotation =
            "Метод вращений Якоби применим только для симметричных матриц $$A_{n*n}$$ $$(A = A^T)$$ и решает полную проблему собственных значений и собственных векторов таких матриц.\n" +
                    "Он основан на отыскании с помощью итерационных процедур матрицы $$U$$ в преобразовании подобия $$\\Lambda = U^{-1}AU$$, а поскольку для симметрических матриц $$A$$ матрица преобразования подобия $$U$$ является ортогональной $$(U^{-1} = U^T)$$, то $$\\Lambda = U^TAU$$, где $$\\Lambda $$ - диагональная матрица с собственными значениями на главной диагонали";
}
