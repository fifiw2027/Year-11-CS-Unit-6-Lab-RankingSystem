public class User {
    private int rank;
    private int progress;

    //constructor
    public User() {
        this.rank = -8;
        this.progress = 0;
    }

    //getter method for rank
    public int getRank() {
        return this.rank;
    }

    //getter method for progress
    public int getProgress() {
        return this.progress;
    }

    public String toString(){
        return "User{rank=" + this.rank + ", progress=" +this.progress + "}";
    }

    //increment progress method
    public void incProgress(int activityRank) {
        // Validate the activity rank
        if (activityRank < -8 || activityRank > 8 || activityRank == 0) {
            throw new IllegalArgumentException("The rank of an activity cannot be less than 8, 0, or greater than 8!");
        }

        // If the user has already reached the max rank (8), progress cannot be incremented
        if (this.rank == 8) {
            return;  // No further progress is allowed
        }

        // Calculate the rank difference while skipping 0
        int rankDifference = calculateRankDifference(this.rank, activityRank);

        if(rankDifference == 0){
            this.progress += 3;
        }
        if(rankDifference == -1){
            this.progress += 1;
        }
        else if (rankDifference > 0){
            this.progress += 10 * rankDifference * rankDifference;
        }

        while(this.progress >= 100){
            this.progress -= 100;
            if(this.rank == 0){
                this.progress = 0;
                break;
            }
            this.rank = incrementRank(this.rank);
        }
    }

    //method to calculate rank difference
    private int calculateRankDifference(int currentRank, int activityRank) {
        if (currentRank < 0 && activityRank > 0) {
            return activityRank - currentRank - 1;
        } else if (currentRank > 0 && activityRank < 0) {
            return activityRank - currentRank + 1;
        }
        return activityRank - currentRank;
    }

    //method to increment rank
    private int incrementRank(int rank) {
        if (rank == -1) {
            return 1;
        }
        return rank + 1;
    }
}
