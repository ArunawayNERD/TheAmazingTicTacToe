package game.ticTacToeGame.frameParts;

/**
 * @author John Pigott
 * Created on 1/9/14 at 1:25 PM
 */
public enum TileState
{
    BLANK(0), X_STATE(1), O_STATE(2);

    private int state;

    private TileState(int state)
    {
        this.state = state;
    }

    public int getState()
    {
        return state;
    }
}
