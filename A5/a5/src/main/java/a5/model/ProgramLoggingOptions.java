package a5.model;

public class ProgramLoggingOptions 
{
    private boolean show_original = true,
                    show_execution = true,
                    show_symbols = true,
                    show_output = true,
                    show_files = true,
                    show_arrows = true,
                    show_index = true,
                    show_heap = true;

    public ProgramLoggingOptions() {};

    public ProgramLoggingOptions
    (
        boolean show_original,
        boolean show_execution,
        boolean show_symbols,
        boolean show_heap,
        boolean show_output,
        boolean show_files,
        boolean show_arrows,
        boolean show_index
    )
    {
        this.show_original = show_original;
        this.show_execution = show_execution;
        this.show_symbols = show_symbols;
        this.show_heap = show_heap;
        this.show_output = show_output;
        this.show_files = show_files;
        this.show_arrows = show_arrows;
        this.show_index = show_index;
    }

    public boolean DisplayOriginalProgram()              { return show_original;  }
    public void    DisplayOriginalProgram(boolean value) { show_original = value; }

    public boolean DisplayExecutionStack()              { return show_execution;  }
    public void    DisplayExecutionStack(boolean value) { show_execution = value; }

    public boolean DisplaySymbolTable()              { return show_symbols;  }
    public void    DisplaySymbolTable(boolean value) { show_symbols = value; }

    public boolean DisplayHeap()              { return show_heap;  }
    public void    DisplayHeap(boolean value) { show_heap = value; }

    public boolean DisplayOutput()              { return show_output;  }
    public void    DisplayOutput(boolean value) { show_output = value; }

    public boolean DisplayFileTable()              { return show_files;  }
    public void    DisplayFileTable(boolean value) { show_files = value; }

    public boolean DisplayDecorationalArrows()              { return show_arrows;  }
    public void    DisplayDecorationalArrows(boolean value) { show_arrows = value; }

    public boolean DisplayIterationIndex()              { return show_index;  }
    public void    DisplayIterationIndex(boolean value) { show_index = value; }
}