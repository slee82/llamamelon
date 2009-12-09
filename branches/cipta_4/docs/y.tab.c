#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#define identifier 257
#define string 258
#define number 259
#define function 260
#define returns 261
#define end 262
#define simfunction 263
#define activate 265
#define print 266
#define if 267
#define do 271
#define foreach 273
#define in 274
#define stopdo 275
#define return 276
#define number 282
#define string 283
#define list 284
#define team 285
#define player 286
#define stat 287
#define nothing 288
#define or 289
#define and 290
#define not 291
#define is 292
#define isnot 293
#define from 298
#define any 299
#define where 301
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    1,    2,    2,    2,    6,    6,    5,    5,
    5,    5,    5,    5,    5,    5,    3,   15,   15,   17,
    4,   12,   13,    7,    7,   19,    8,    8,    8,    9,
    9,    9,   14,   20,   20,   20,   20,   20,   20,   10,
   21,   21,   22,   22,   16,   16,   16,   16,   16,   16,
   16,   11,   11,   18,   23,   23,   24,   24,   25,   25,
   26,   26,   26,   26,   26,   26,   26,   27,   27,   27,
   28,   28,   28,   28,   29,   29,   29,   29,   29,   30,
   30,   30,   30,   30,   31,   31,   33,   33,   34,   34,
   32,   32,   32,   32,   32,   32,   35,   35,   36,   36,
};
short yylen[] = {                                         2,
    1,    1,    2,    1,    1,    1,    1,    2,    1,    1,
    1,    1,    1,    1,    1,    1,   10,    1,    3,    2,
    5,    3,    3,    7,    8,    2,    3,    5,    7,    2,
    3,    2,    4,    1,    1,    1,    1,    1,    1,    3,
    1,    3,    1,    3,    1,    1,    1,    1,    1,    1,
    1,    1,    2,    1,    1,    3,    1,    3,    1,    2,
    1,    3,    3,    3,    3,    3,    3,    1,    3,    3,
    1,    3,    3,    3,    1,    2,    2,    3,    2,    1,
    3,    5,    2,    2,    1,    1,    3,    4,    1,    3,
    1,    1,    1,    1,    1,    3,    3,    2,    1,    3,
};
short yydefred[] = {                                      0,
    0,   93,   92,    0,    0,    0,    0,   52,    0,    0,
    0,    0,    0,    0,    0,   45,   46,   47,   48,   49,
   50,    0,    0,    0,    0,    0,    0,    0,    0,    2,
    4,    5,    6,    9,   10,   11,   12,   13,   14,   15,
   16,    0,    0,    0,    0,   57,    0,    0,    0,   71,
    0,    0,   85,   86,   94,    0,   34,   35,   36,   37,
   38,   39,    0,    0,    0,   95,    0,    0,    0,    0,
    0,    7,    0,    0,    0,   30,   32,    0,   60,   76,
   77,   79,   98,   99,    0,    3,    0,    0,   41,   53,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   83,   84,    0,    0,    0,   87,   89,
    0,    0,    0,   96,    0,   22,   23,    0,   27,    8,
    0,    0,   31,    0,   97,    0,    0,   40,    0,   58,
    0,    0,    0,    0,    0,    0,    0,    0,   72,   73,
   74,   81,    0,   78,   88,    0,   33,   51,    0,    0,
   18,    0,    0,    0,    0,  100,   44,   42,    0,   90,
    0,    0,   20,   21,    0,   28,    0,   82,    0,   19,
    0,    0,    0,   24,    0,    0,   29,    0,    0,   25,
    0,   17,
};
short yydgoto[] = {                                      28,
   29,   30,   31,   32,   72,   73,   34,   35,   36,   37,
   38,   39,   40,   41,  149,   42,  151,   43,  176,   63,
   88,   89,   44,   45,   46,   47,   48,   49,   50,   51,
   52,   53,   54,  111,   55,   85,
};
short yysindex[] = {                                     82,
   20,    0,    0, -243,  424, -233, -229,    0,  424,   -4,
  403,  424, -227,  -19,  -21,    0,    0,    0,    0,    0,
    0,    0,  424,  448,  448,  448,   63,    0,   82,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -218,   -2, -234, -226,    0,  -51,   55,   67,    0,
 -254, -225,    0,    0,    0,  255,    0,    0,    0,    0,
    0,    0,  424,   49,   52,    0,   36, -178,   34,   48,
  424,    0,  154, -164, -163,    0,    0,   53,    0,    0,
    0,    0,    0,    0,  -31,    0,   57,  -43,    0,    0,
  424,  424,  448,  448,  448,  448,  448,  448,  448,  448,
  448,  448,  448,    0,    0, -144,   76,  448,    0,    0,
  -26,   60, -153,    0,  403,    0,    0,   79,    0,    0,
  403, -136,    0,  424,    0,  424, -218,    0, -226,    0,
   55,   55,   55,   55,   55,   55,   67,   67,    0,    0,
    0,    0,  424,    0,    0,  424,    0,    0,  -15, -134,
    0,  190, -142,  234,   66,    0,    0,    0,   86,    0,
 -125, -153,    0,    0,  403,    0,  403,    0, -153,    0,
  118,  304,   80,    0,  403, -120,    0,  403,  403,    0,
  367,    0,
};
short yyrindex[] = {                                      0,
   37,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   16,    0,    0,    0,    0,    0,    0,  137,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   46,   47,    0,  483,  523,  553,    0,
  690,  -10,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  -37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  -42,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  146,    0,
  636,  726,  740,  764,  790,  800,  681,  716,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -119,    0,
    0,    0,
};
short yygindex[] = {                                      0,
    0,  119,    0,    0,   12,  -50,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -68,  -13,   39,    0,    0,
    0,   23,    0,   56,  -20,    0,   74,  -79,   43,    0,
    0,    0,    0,    0,    0,    0,
};
#define YYTABLESIZE 1095
short yytable[] = {                                      91,
  127,   43,   79,   91,   91,   91,   91,   91,   96,   91,
   95,   33,  124,   64,  145,  128,   43,  146,    5,  137,
  138,   91,   91,   68,   91,  161,   80,   69,  162,   75,
   80,   80,   80,   80,   80,   71,   80,   77,   87,   76,
   33,  104,  105,   67,  150,  106,  107,   70,   80,   80,
   74,   80,   95,   78,   91,   91,   90,   95,   95,   56,
   95,  125,   95,   92,  152,   84,   80,   81,   82,   27,
  154,  130,  108,   91,   95,   95,  114,   95,   91,   91,
   57,   91,   80,   91,  120,  115,   54,   55,  113,   54,
   55,   56,  116,  150,  110,   91,   91,   99,   91,  100,
  173,  112,    5,  103,   54,   55,  117,  121,  101,  118,
  122,  123,  142,  102,  171,  143,  172,  126,  147,  153,
  155,    5,  163,  167,  179,  165,  168,  181,   16,   17,
   18,   19,   20,   21,  148,  169,    1,  178,   54,   55,
    8,  180,   26,  139,  140,  141,  129,   86,  170,  158,
  144,    0,    0,   27,    0,   83,    0,    5,    0,    0,
    0,    0,  156,  120,  157,  120,  131,  132,  133,  134,
  135,  136,   27,    0,    0,    0,    8,    0,    0,    0,
    0,  159,  120,  120,  160,    0,   56,    0,    0,   56,
  120,    0,  120,    5,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   56,    0,    0,    0,   27,    0,
    0,    0,    8,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    5,
    0,    0,    0,    0,   91,   65,    2,    3,   56,    0,
   93,   94,   97,   98,   27,    0,    0,    0,    8,    0,
    0,   91,   91,    0,   91,   91,   91,   91,   91,   91,
   91,   80,   91,   91,    0,    0,   66,    0,    0,   23,
    0,    0,   51,    5,   24,   25,    0,   26,   80,   80,
   27,   80,   80,   80,   80,   80,   80,    0,    0,   80,
   80,    0,    8,    0,    5,  109,   58,   59,   60,   61,
   62,    0,    0,    0,   95,   95,    0,   95,   95,   95,
   95,   95,   95,   95,    0,   95,   95,   54,   55,   65,
    2,    3,    0,    0,   27,   91,   91,    0,   91,   91,
   91,   91,   91,   91,   91,   55,   91,   91,    1,    2,
    3,    4,    0,    5,    6,   27,    7,    9,   10,    0,
   66,   11,   12,   23,   13,    0,   14,   15,   24,   25,
    0,   26,    8,   16,   17,   18,   19,   20,   21,   22,
    0,    0,   23,    0,    1,    2,    3,   24,   25,  174,
   26,    0,    7,    9,   10,    0,  175,   11,   12,    0,
   13,    0,   14,   15,   27,    0,    0,    0,    0,   16,
   17,   18,   19,   20,   21,   22,    5,    0,   23,    0,
    1,    2,    3,   24,   25,  119,   26,   56,    7,    9,
   10,    0,    0,   11,   12,    8,   13,    0,   14,   15,
    0,    0,    0,    0,   56,   16,   17,   18,   19,   20,
   21,   22,    5,    0,   23,    0,    1,    2,    3,   24,
   25,  164,   26,    0,    7,    9,   10,   27,    0,   11,
   12,    8,   13,    5,   14,   15,    0,    0,    0,    0,
    0,   16,   17,   18,   19,   20,   21,   22,    0,    0,
   23,    0,    0,    0,    0,   24,   25,    5,   26,    0,
    1,    2,    3,   27,    0,  166,    0,    0,    7,    9,
   10,    0,    0,   11,   12,    0,   13,    0,   14,   15,
    0,   65,    2,    3,   27,   16,   17,   18,   19,   20,
   21,   22,    0,   59,   23,    0,   59,    0,    0,   24,
   25,    0,   26,    0,    0,    0,    0,    0,   27,    0,
    0,   59,   66,    0,    0,   23,    0,    0,    0,    0,
   24,   25,    0,   26,    0,    0,    0,    0,    0,    0,
    1,    2,    3,   61,    0,  177,   61,    0,    7,    9,
   10,    0,    0,   11,   12,   59,   13,    0,   14,   15,
    0,   61,   61,    0,   61,   16,   17,   18,   19,   20,
   21,   22,    0,   68,   23,   68,   68,   68,    0,   24,
   25,    0,   26,    0,    0,    0,    0,    0,    0,    0,
    0,   68,   68,    0,   68,   61,    0,    0,    0,    0,
    0,    0,    0,    1,    2,    3,    0,    0,  182,    0,
    0,    7,    9,   10,    0,    0,   11,   12,    0,   13,
    0,   14,   15,    0,    0,   68,    0,    0,   16,   17,
   18,   19,   20,   21,   22,    0,    0,   23,    0,    1,
    2,    3,   24,   25,    0,   26,    0,    7,    9,   10,
    0,    0,   11,   12,    0,   13,   62,   14,   15,   62,
   65,    2,    3,    0,   16,   17,   18,   19,   20,   21,
   22,    0,    0,   23,   62,   62,    0,   62,   24,   25,
    0,   26,    0,    0,   65,    2,    3,    0,    0,    0,
    0,   66,    0,    0,   23,    0,    0,    0,    0,   24,
   25,   69,   26,   69,   69,   69,   75,    0,   62,    0,
   75,   75,   75,   75,   75,   66,   75,    0,    0,   69,
   69,    0,   69,   24,   25,    0,   26,    0,   75,   75,
    0,   75,    0,    0,   59,    0,   70,    0,   70,   70,
   70,    0,    0,    0,    0,    0,   63,    0,    0,   63,
    0,   59,   59,   69,   70,   70,    0,   70,    0,    0,
   64,    0,   75,   64,   63,   63,    0,   63,    0,    0,
    0,    0,    0,    0,   61,    0,    0,    0,   64,   64,
    0,   64,    0,    0,   65,    0,    0,   65,   70,    0,
    0,   61,   61,    0,   61,   61,   61,   61,   63,    0,
    0,    0,   65,   65,   68,   65,    0,    0,    0,    0,
   66,    0,   64,   66,    0,    0,    0,    0,    0,    0,
   67,   68,   68,   67,   68,   68,   68,   68,   66,   66,
    0,   66,    0,    0,    0,    0,   65,    0,   67,   67,
    0,   67,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   66,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   67,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   62,   62,    0,   62,   62,   62,
   62,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   69,    0,    0,    0,    0,    0,    0,    0,
    0,   75,    0,    0,    0,    0,    0,    0,    0,   69,
   69,    0,   69,   69,   69,   69,    0,    0,   75,   75,
    0,   75,   75,   75,   75,    0,    0,   70,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   63,    0,    0,
    0,    0,    0,    0,   70,   70,    0,   70,   70,   70,
   70,   64,    0,    0,   63,   63,    0,   63,   63,   63,
   63,    0,    0,    0,    0,    0,    0,    0,   64,   64,
    0,   64,   64,   64,   64,   65,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   65,   65,    0,   65,   65,   65,   65,    0,
    0,   66,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   67,    0,    0,    0,    0,    0,    0,   66,   66,
    0,   66,   66,   66,   66,    0,    0,    0,   67,   67,
    0,   67,   67,   67,   67,
};
short yycheck[] = {                                      37,
   44,   44,   23,   41,   42,   43,   44,   45,   60,   47,
   62,    0,   44,  257,   41,   59,   59,   44,   40,   99,
  100,   59,   60,  257,   62,   41,   37,  257,   44,  257,
   41,   42,   43,   44,   45,   40,   47,   59,  257,   59,
   29,  296,  297,    5,  113,  300,  301,    9,   59,   60,
   12,   62,   37,   15,  289,   93,   59,   42,   43,   40,
   45,   93,   47,  290,  115,   27,   24,   25,   26,   91,
  121,   92,  298,   37,   59,   60,   41,   62,   42,   43,
   61,   45,   93,   47,   73,  264,   41,   41,   40,   44,
   44,   40,   59,  162,   56,   59,   60,   43,   62,   45,
  169,   63,   40,   37,   59,   59,   59,  272,   42,   71,
  274,   59,  257,   47,  165,   40,  167,   61,   59,   41,
  257,   40,  257,   58,  175,  268,   41,  178,  282,  283,
  284,  285,  286,  287,  288,  261,    0,   58,   93,   93,
   59,  262,  262,  101,  102,  103,   91,   29,  162,  127,
  108,   -1,   -1,   91,   -1,   93,   -1,   40,   -1,   -1,
   -1,   -1,  124,  152,  126,  154,   93,   94,   95,   96,
   97,   98,   91,   -1,   -1,   -1,   59,   -1,   -1,   -1,
   -1,  143,  171,  172,  146,   -1,   41,   -1,   -1,   44,
  179,   -1,  181,   40,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   59,   -1,   -1,   -1,   91,   -1,
   -1,   -1,   59,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   40,
   -1,   -1,   -1,   -1,  272,  257,  258,  259,   93,   -1,
  292,  293,  294,  295,   91,   -1,   -1,   -1,   59,   -1,
   -1,  289,  290,   -1,  292,  293,  294,  295,  296,  297,
  298,  272,  300,  301,   -1,   -1,  288,   -1,   -1,  291,
   -1,   -1,  257,   40,  296,  297,   -1,  299,  289,  290,
   91,  292,  293,  294,  295,  296,  297,   -1,   -1,  300,
  301,   -1,   59,   -1,   40,   41,  277,  278,  279,  280,
  281,   -1,   -1,   -1,  289,  290,   -1,  292,  293,  294,
  295,  296,  297,  298,   -1,  300,  301,  272,  272,  257,
  258,  259,   -1,   -1,   91,  289,  290,   -1,  292,  293,
  294,  295,  296,  297,  298,  289,  300,  301,  257,  258,
  259,  260,   -1,   40,  263,   91,  265,  266,  267,   -1,
  288,  270,  271,  291,  273,   -1,  275,  276,  296,  297,
   -1,  299,   59,  282,  283,  284,  285,  286,  287,  288,
   -1,   -1,  291,   -1,  257,  258,  259,  296,  297,  262,
  299,   -1,  265,  266,  267,   -1,  269,  270,  271,   -1,
  273,   -1,  275,  276,   91,   -1,   -1,   -1,   -1,  282,
  283,  284,  285,  286,  287,  288,   40,   -1,  291,   -1,
  257,  258,  259,  296,  297,  262,  299,  272,  265,  266,
  267,   -1,   -1,  270,  271,   59,  273,   -1,  275,  276,
   -1,   -1,   -1,   -1,  289,  282,  283,  284,  285,  286,
  287,  288,   40,   -1,  291,   -1,  257,  258,  259,  296,
  297,  262,  299,   -1,  265,  266,  267,   91,   -1,  270,
  271,   59,  273,   40,  275,  276,   -1,   -1,   -1,   -1,
   -1,  282,  283,  284,  285,  286,  287,  288,   -1,   -1,
  291,   -1,   -1,   -1,   -1,  296,  297,   40,  299,   -1,
  257,  258,  259,   91,   -1,  262,   -1,   -1,  265,  266,
  267,   -1,   -1,  270,  271,   -1,  273,   -1,  275,  276,
   -1,  257,  258,  259,   91,  282,  283,  284,  285,  286,
  287,  288,   -1,   41,  291,   -1,   44,   -1,   -1,  296,
  297,   -1,  299,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   59,  288,   -1,   -1,  291,   -1,   -1,   -1,   -1,
  296,  297,   -1,  299,   -1,   -1,   -1,   -1,   -1,   -1,
  257,  258,  259,   41,   -1,  262,   44,   -1,  265,  266,
  267,   -1,   -1,  270,  271,   93,  273,   -1,  275,  276,
   -1,   59,   60,   -1,   62,  282,  283,  284,  285,  286,
  287,  288,   -1,   41,  291,   43,   44,   45,   -1,  296,
  297,   -1,  299,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   60,   -1,   62,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  257,  258,  259,   -1,   -1,  262,   -1,
   -1,  265,  266,  267,   -1,   -1,  270,  271,   -1,  273,
   -1,  275,  276,   -1,   -1,   93,   -1,   -1,  282,  283,
  284,  285,  286,  287,  288,   -1,   -1,  291,   -1,  257,
  258,  259,  296,  297,   -1,  299,   -1,  265,  266,  267,
   -1,   -1,  270,  271,   -1,  273,   41,  275,  276,   44,
  257,  258,  259,   -1,  282,  283,  284,  285,  286,  287,
  288,   -1,   -1,  291,   59,   60,   -1,   62,  296,  297,
   -1,  299,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,
   -1,  288,   -1,   -1,  291,   -1,   -1,   -1,   -1,  296,
  297,   41,  299,   43,   44,   45,   37,   -1,   93,   -1,
   41,   42,   43,   44,   45,  288,   47,   -1,   -1,   59,
   60,   -1,   62,  296,  297,   -1,  299,   -1,   59,   60,
   -1,   62,   -1,   -1,  272,   -1,   41,   -1,   43,   44,
   45,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,  289,  290,   93,   59,   60,   -1,   62,   -1,   -1,
   41,   -1,   93,   44,   59,   60,   -1,   62,   -1,   -1,
   -1,   -1,   -1,   -1,  272,   -1,   -1,   -1,   59,   60,
   -1,   62,   -1,   -1,   41,   -1,   -1,   44,   93,   -1,
   -1,  289,  290,   -1,  292,  293,  294,  295,   93,   -1,
   -1,   -1,   59,   60,  272,   62,   -1,   -1,   -1,   -1,
   41,   -1,   93,   44,   -1,   -1,   -1,   -1,   -1,   -1,
   41,  289,  290,   44,  292,  293,  294,  295,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   93,   -1,   59,   60,
   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  289,  290,   -1,  292,  293,  294,
  295,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  289,
  290,   -1,  292,  293,  294,  295,   -1,   -1,  289,  290,
   -1,  292,  293,  294,  295,   -1,   -1,  272,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  272,   -1,   -1,
   -1,   -1,   -1,   -1,  289,  290,   -1,  292,  293,  294,
  295,  272,   -1,   -1,  289,  290,   -1,  292,  293,  294,
  295,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  289,  290,
   -1,  292,  293,  294,  295,  272,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  289,  290,   -1,  292,  293,  294,  295,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  272,   -1,   -1,   -1,   -1,   -1,   -1,  289,  290,
   -1,  292,  293,  294,  295,   -1,   -1,   -1,  289,  290,
   -1,  292,  293,  294,  295,
};
#define YYFINAL 28
#ifndef YYDEBUG
#define YYDEBUG 0
#endif
#define YYMAXTOKEN 301
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,"'%'",0,0,"'('","')'","'*'","'+'","','","'-'",0,"'/'",0,0,0,0,0,0,0,0,0,0,
"':'","';'","'<'","'='","'>'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,"'['",0,"']'",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,"identifier","string","number","\"function\"",
"\"returns\"","\"end\"","\"simfunction\"","\"is:\"","\"activate\"","\"print\"",
"\"if\"","\"then:\"","\"else:\"","\"do:\"","\"do\"","\"times:\"","\"foreach\"",
"\"in\"","\"stopdo\"","\"return\"","\"+=\"","\"-=\"","\"*=\"","\"/=\"","\"%=\"",
"\"number\"","\"string\"","\"list\"","\"team\"","\"player\"","\"stat\"",
"\"nothing\"","\"or\"","\"and\"","\"not\"","\"is\"","\"isnot\"","\">=\"",
"\"<=\"","\"++\"","\"--\"","\"from\"","\"any\"","\"'s\"","\"where\"",
};
char *yyrule[] = {
"$accept : program",
"program : statement_list",
"statement_list : statement",
"statement_list : statement_list statement",
"statement : function_definition",
"statement : sim_function_definition",
"statement : body_statement",
"body_statement_list : body_statement",
"body_statement_list : body_statement_list body_statement",
"body_statement : if_statement",
"body_statement : iteration_statement",
"body_statement : jump_statement",
"body_statement : declaration",
"body_statement : expression_statement",
"body_statement : activate_statement",
"body_statement : print_statement",
"body_statement : assignment_statement",
"function_definition : \"function\" identifier '(' parameter_list ')' \"returns\" type ':' body_statement_list \"end\"",
"parameter_list : parameter",
"parameter_list : parameter_list ',' parameter",
"parameter : type identifier",
"sim_function_definition : \"simfunction\" identifier \"is:\" body_statement_list \"end\"",
"activate_statement : \"activate\" identifier ';'",
"print_statement : \"print\" expression ';'",
"if_statement : \"if\" '(' expression ')' \"then:\" body_statement_list \"end\"",
"if_statement : \"if\" '(' expression ')' \"then:\" body_statement_list else_statement \"end\"",
"else_statement : \"else:\" body_statement_list",
"iteration_statement : \"do:\" body_statement_list \"end\"",
"iteration_statement : \"do\" expression \"times:\" body_statement_list \"end\"",
"iteration_statement : \"foreach\" identifier \"in\" identifier ':' body_statement_list \"end\"",
"jump_statement : \"stopdo\" ';'",
"jump_statement : \"return\" expression ';'",
"jump_statement : \"return\" ';'",
"assignment_statement : identifier assignment_operator expression ';'",
"assignment_operator : '='",
"assignment_operator : \"+=\"",
"assignment_operator : \"-=\"",
"assignment_operator : \"*=\"",
"assignment_operator : \"/=\"",
"assignment_operator : \"%=\"",
"declaration : type variable_declarators ';'",
"variable_declarators : variable_declarator",
"variable_declarators : variable_declarators ',' variable_declarator",
"variable_declarator : identifier",
"variable_declarator : identifier '=' expression",
"type : \"number\"",
"type : \"string\"",
"type : \"list\"",
"type : \"team\"",
"type : \"player\"",
"type : \"stat\"",
"type : \"nothing\"",
"expression_statement : ';'",
"expression_statement : expression ';'",
"expression : logical_or_expression",
"logical_or_expression : logical_and_expression",
"logical_or_expression : logical_or_expression \"or\" logical_and_expression",
"logical_and_expression : logical_not_expression",
"logical_and_expression : logical_and_expression \"and\" logical_not_expression",
"logical_not_expression : comparison_expression",
"logical_not_expression : \"not\" logical_not_expression",
"comparison_expression : addition_expression",
"comparison_expression : comparison_expression \"is\" addition_expression",
"comparison_expression : comparison_expression \"isnot\" addition_expression",
"comparison_expression : comparison_expression '>' addition_expression",
"comparison_expression : comparison_expression '<' addition_expression",
"comparison_expression : comparison_expression \">=\" addition_expression",
"comparison_expression : comparison_expression \"<=\" addition_expression",
"addition_expression : multiplication_expression",
"addition_expression : addition_expression '+' multiplication_expression",
"addition_expression : addition_expression '-' multiplication_expression",
"multiplication_expression : unary_expression",
"multiplication_expression : multiplication_expression '*' unary_expression",
"multiplication_expression : multiplication_expression '/' unary_expression",
"multiplication_expression : multiplication_expression '%' unary_expression",
"unary_expression : postfix_expression",
"unary_expression : \"++\" unary_expression",
"unary_expression : \"--\" unary_expression",
"unary_expression : primary_expression \"from\" unary_expression",
"unary_expression : \"any\" unary_expression",
"postfix_expression : primary_expression",
"postfix_expression : postfix_expression \"'s\" identifier",
"postfix_expression : postfix_expression \"where\" '(' expression ')'",
"postfix_expression : postfix_expression \"++\"",
"postfix_expression : postfix_expression \"--\"",
"primary_expression : atom_expression",
"primary_expression : function_call",
"function_call : identifier '(' ')'",
"function_call : identifier '(' argument_list ')'",
"argument_list : expression",
"argument_list : argument_list ',' expression",
"atom_expression : identifier",
"atom_expression : number",
"atom_expression : string",
"atom_expression : list_initializer",
"atom_expression : \"nothing\"",
"atom_expression : '(' expression ')'",
"list_initializer : '[' variable_list ']'",
"list_initializer : '[' ']'",
"variable_list : expression",
"variable_list : variable_list ',' expression",
};
#endif
#ifndef YYSTYPE
typedef int YYSTYPE;
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
