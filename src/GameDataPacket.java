//Custom data object for game data
public class GameDataPacket implements java.io.Serializable{

    private float p1y, p2y, ballx, bally, ballxVelocity, ballyVelocity;

    public GameDataPacket(float p1y, float p2y, float ballx, float bally, float ballxVelocity, float ballyVelocity){
        this.p1y = p1y;
        this.p2y = p2y;
        this.ballx = ballx;
        this.bally = bally;
        this.ballxVelocity = ballxVelocity;
        this.ballyVelocity = ballyVelocity;
    }
    //Standard getters and setters
    public float getP1y() {
        return p1y;
    }

    public void setP1y(float p1y) {
        this.p1y = p1y;
    }

    public float getP2y() {
        return p2y;
    }

    public void setP2y(float p2y) {
        this.p2y = p2y;
    }

    public float getBallx() {
        return ballx;
    }

    public void setBallx(float ballx) {
        this.ballx = ballx;
    }

    public float getBally() {
        return bally;
    }

    public void setBally(float bally) {
        this.bally = bally;
    }

    public float getBallxVelocity() {
        return ballxVelocity;
    }

    public void setBallxVelocity(float ballxVelocity) {
        this.ballxVelocity = ballxVelocity;
    }

    public float getBallyVelocity() {
        return ballyVelocity;
    }

    public void setBallyVelocity(float ballyVelocity) {
        this.ballyVelocity = ballyVelocity;
    }

}
