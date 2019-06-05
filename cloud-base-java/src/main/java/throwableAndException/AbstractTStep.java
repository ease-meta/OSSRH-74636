package throwableAndException;

public abstract class AbstractTStep implements ITStep {
    @Override
    public String execute() throws Exception {
        try {
            this.exe();
        } catch (Exception throwable) {
            throwable.printStackTrace();
            throw new BatchException("批量执行错误！错误信息：" + throwable);
        }

        return "OK";
    }

    public abstract void exe() throws Exception;
}