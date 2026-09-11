package es.unican.munidiff.rama;

import es.unican.munidiff.rama.app.RamaApplication;
import es.unican.munidiff.rama.comparison.*;
import es.unican.munidiff.rama.config.*;
import es.unican.munidiff.rama.git.GitService;
import es.unican.munidiff.rama.git.github.GitHubService;
import es.unican.munidiff.rama.render.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // The GitHub Action passes the pull request number as the first argument.
        // RAMA uses that PR number to fetch changed files and publish the final report.
        int prNumber = Integer.parseInt(args[0]);

        RamaConfigLoader configService = new RamaConfigLoader();
        RamaConfigLoadResult configuration = configService.loadConfig();
        RamaConfig config = configuration.config();

        GitService gitService = GitHubService.fromEnvironment(config);
        ComparisonService modelComparator = new EmfModelComparator(config, configService.getWorkspacePath());

        RamaApplication application = new RamaApplication(
                config,
                gitService,
                modelComparator,
                new MunidiffRenderer(),
                new ReportCommentRenderer(new PlantUMLEncoderService()),
                configuration.warning()
        );
        application.run(prNumber);
    }
}
