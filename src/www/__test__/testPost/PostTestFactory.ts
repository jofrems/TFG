import { PostLineStep } from "./PostLineStep";
import { PostTestRunner } from "./PostTestRunner";
import { readPost } from "./readPost";
import { snapshotService } from "./SnapshotService";

export class PostTestFactory {
  public create(postId: string, postLineSteps: PostLineStep[]) {
    var tests = readPost(postId);
    tests.forEach((postTest) => {
      test(`${postTest.toPrettyName()}`, async () => {
        snapshotService.read(postId, postTest.getTestId());
        await new PostTestRunner(postTest, postLineSteps).run();
      });
    });
  }
}
