package a7.model;

import a7.controller.Controller;
import a7.repo.Repository;

import a7.model.ADT.implementations.AddressHeap;
import a7.model.ADT.implementations.Dictionary;
import a7.model.ADT.implementations.List;
import a7.model.ADT.implementations.Stack;
import a7.model.expressions.*;
import a7.model.statements.*;
import a7.model.values.*;
import a7.model.types.*;

public final class ProgramTemplates 
{
    private ProgramTemplates() { }

    public static Controller TemplateOne(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new IntType()),
                new CompoundStatement
                (
                    new AssignmentStatement("v", new ConstantExpression(new IntValue(2))),
                    new PrintStatement(new VariableExpression("v"))
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "Simple assignment template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateTwo(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("a", new IntType()),
                new CompoundStatement
                (
                    new DeclarationStatement("b", new IntType()),
                    new CompoundStatement
                    (
                        new AssignmentStatement("a", 
                                                new ArithmeticExpression(new ConstantExpression(new IntValue(2)),
                                                                        ArithmeticOperator.PLUS,
                                                                        new ArithmeticExpression(new ConstantExpression(new IntValue(3)),
                                                                                                ArithmeticOperator.MULTIPLY,
                                                                                                new ConstantExpression(new IntValue(5))))),
                        new CompoundStatement
                        (
                            new AssignmentStatement("b",
                                                    new ArithmeticExpression(new VariableExpression("a"),
                                                                            ArithmeticOperator.PLUS,
                                                                            new ConstantExpression(new IntValue(1)))),
                            new PrintStatement(new VariableExpression("b"))
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );
        
        Repository repo = new Repository(program, log_path, "Arithmetic expressions template");
        Controller ctrl = new Controller(repo);
        
        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateThree(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("a", new BoolType()),
                new CompoundStatement
                (
                    new DeclarationStatement("v", new IntType()),
                    new CompoundStatement
                    (
                        new AssignmentStatement("a", 
                                                new ConstantExpression(new BoolValue(true))),
                        new CompoundStatement
                        (
                            new ConditionalStatement(new VariableExpression("a"), 
                                                    new AssignmentStatement("v",
                                                                            new ConstantExpression(new IntValue(2))),
                                                    new AssignmentStatement("v", 
                                                                            new ConstantExpression(new IntValue(3)))),
                            new PrintStatement(new VariableExpression("v"))
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "Conditional statement template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateFour(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("varf", new StringType()),
                new CompoundStatement
                (
                    new AssignmentStatement("varf", 
                                            new ConstantExpression(new StringValue("C:\\Users\\marius\\Desktop\\test2.txt"))),
                    new CompoundStatement
                    (
                        new OpenReadFileStatement(new VariableExpression("varf")),
                        new CompoundStatement
                        (
                            new DeclarationStatement("varc", new IntType()),
                            new CompoundStatement
                            (
                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                new CompoundStatement
                                (
                                    new PrintStatement(new VariableExpression("varc")),
                                    new CompoundStatement
                                    (
                                        new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                        new CompoundStatement
                                        (
                                            new PrintStatement(new VariableExpression("varc")),
                                            new CloseReadFileStatement(new VariableExpression("varf"))
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

		Repository repo = new Repository(program, log_path, "File read template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateFive(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("a", new IntType()),
                new CompoundStatement
                (
                    new DeclarationStatement("b", new IntType()),
                    new CompoundStatement
                    (
                        new AssignmentStatement("a", new ConstantExpression(new IntValue(0))),
                        new CompoundStatement
                        (
                            new AssignmentStatement("b", new ConstantExpression(new IntValue(1))),
                            new PrintStatement(new RelationalExpression(new VariableExpression("a"), 
                                                                        RelationalOperator.EQUAL, 
                                                                        new VariableExpression("b")))
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

		Repository repo = new Repository(program, log_path, "Relational expression template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateSix(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement
                (
                    new AllocationStatement("v", new ConstantExpression(new IntValue(20))),
                    new CompoundStatement
                    (
                        new DeclarationStatement("a", new RefType(new RefType(new IntType()))),
                        new CompoundStatement
                        (
                            new AllocationStatement("a", new VariableExpression("v")),
                            new CompoundStatement
                            (
                                new PrintStatement(new VariableExpression("v")),
                                new PrintStatement(new VariableExpression("a"))
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "Heap allocation template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateSeven(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement
                (
                    new AllocationStatement("v", new ConstantExpression(new IntValue(20))),
                    new CompoundStatement
                    (
                        new DeclarationStatement("a", new RefType(new RefType(new IntType()))),
                        new CompoundStatement
                        (
                            new AllocationStatement("a", new VariableExpression("v")),
                            new CompoundStatement
                            (
                                new PrintStatement(new DereferenceExpression(new VariableExpression("v"))),
                                new PrintStatement
                                (
                                    new ArithmeticExpression
                                    (
                                        new DereferenceExpression(new DereferenceExpression(new VariableExpression("a"))), 
                                        ArithmeticOperator.PLUS,
                                        new ConstantExpression(new IntValue(5)))
                                )
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );
        
        Repository repo = new Repository(program, log_path, "Dereference template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateEight(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement
                (
                    new AllocationStatement("v", new ConstantExpression(new IntValue(20))),
                    new CompoundStatement
                    (
                        new PrintStatement(new DereferenceExpression(new VariableExpression("v"))),
                        new CompoundStatement
                        (
                            new RefAssignmentStatement("v", new ConstantExpression(new IntValue(30))),
                            new PrintStatement
                            (
                                new ArithmeticExpression
                                (
                                    new DereferenceExpression(new VariableExpression("v")), 
                                    ArithmeticOperator.PLUS,
                                    new ConstantExpression(new IntValue(5))
                                )
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "Reference assignment template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateNine(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement
                (
                    new AllocationStatement("v", new ConstantExpression(new IntValue(20))),
                    new CompoundStatement
                    (
                        new DeclarationStatement("a", new RefType(new RefType(new IntType()))),
                        new CompoundStatement
                        (
                            new AllocationStatement("a", new VariableExpression("v")),
                            new CompoundStatement
                            (
                                new AllocationStatement("v", new ConstantExpression(new IntValue(30))),
                                new CompoundStatement
                                (
                                    new PrintStatement(new DereferenceExpression(new VariableExpression("v"))),
                                    new PrintStatement(new DereferenceExpression(new DereferenceExpression(new VariableExpression("a"))))
                                )
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "GC template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateTen(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new IntType()),
                new CompoundStatement
                (
                    new AssignmentStatement("v", new ConstantExpression(new IntValue(4))),
                    new CompoundStatement
                    (
                        new WhileStatement
                        (
                            new RelationalExpression
                            (
                                new VariableExpression("v"),
                                RelationalOperator.GREATER,
                                new ConstantExpression(new IntValue(0))
                            ),

                            new CompoundStatement
                            (
                                new PrintStatement(new VariableExpression("v")),
                                new AssignmentStatement("v", new ArithmeticExpression(new VariableExpression("v"),
                                                                                              ArithmeticOperator.MINUS,
                                                                                              new ConstantExpression(new IntValue(1))))
                            )
                        ),
                        new PrintStatement(new VariableExpression("v"))
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "While statement template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }

    public static Controller TemplateEleven(String log_path)
    {
        ProgramState program = new ProgramState
        (
            new CompoundStatement
            (
                new DeclarationStatement("v", new IntType()),
                new CompoundStatement
                (
                    new DeclarationStatement("a", new RefType(new IntType())),
                    new CompoundStatement
                    (
                        new AssignmentStatement("v", new ConstantExpression(new IntValue(10))),
                        new CompoundStatement
                        (
                            new AllocationStatement("a", new ConstantExpression(new IntValue(22))),
                            new CompoundStatement
                            (
                                new ForkStatement
                                (
                                    new CompoundStatement
                                    (
                                        new RefAssignmentStatement("a", new ConstantExpression(new IntValue(30))),
                                        new CompoundStatement
                                        (
                                            new AssignmentStatement("v", new ConstantExpression(new IntValue(32))),
                                            new CompoundStatement
                                            (
                                                new PrintStatement(new VariableExpression("v")),
                                                new PrintStatement(new DereferenceExpression(new VariableExpression("a")))
                                            )
                                        )
                                    )
                                ),
                                new CompoundStatement
                                (
                                    new PrintStatement(new VariableExpression("v")),
                                    new PrintStatement(new DereferenceExpression(new VariableExpression("a")))
                                )
                            )
                        )
                    )
                )
            ),

            new Stack<>(),
            new Dictionary<>(),
            new AddressHeap<>(),
            new List<>(),
            new Dictionary<>()
        );

        Repository repo = new Repository(program, log_path, "Fork template");
        Controller ctrl = new Controller(repo);

        ctrl.ToggleLogFlag();
        ctrl.ToggleDisplayFlag();

        return ctrl;
    }
}