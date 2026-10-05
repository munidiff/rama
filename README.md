# RAMA - pull Request Augmentation for Model Awareness

Automated analysis of model and metamodel changes in GitHub Pull Requests.

RAMA is a **GitHub Actions-based tool** that detects, analyzes, and reports changes in EMF models and metamodels in pull requests. Models are compared at model-level via [EMF Compare](https://eclipse.dev/emfcompare/), and munidiff-based ([1](https://doi.org/10.1109/MODELS-C59198.2023.00145), [2](https://dl.acm.org/doi/10.1145/3652620.3688207)) difference reports are automatically included as comments in the analyzed pull request discussion.

## Quick start

RAMA is designed to run in the repository whose pull requests it analyzes.

1. Add a `rama.json` file to the root of the target repository. Start with the [configuration](#configuration) below and adapt its model extensions and metamodel paths.
2. Copy [`rama.example.yml`](rama.example.yml) to `.github/workflows/rama.yml` in that repository.
3. Open or update a pull request that modifies a configured model or metamodel file. The workflow builds RAMA, retrieves the changed files through the GitHub API, and posts its report as a pull request comment.

The workflow template clones this repository from `https://github.com/munidiff/rama`. If you use a fork of RAMA, replace that URL `.github/workflows/rama.yml`.

## Configuration

RAMA has to be configured in the target repository. The target repository is the repository where the GitHub Action runs and whose pull requests RAMA analyzes. The workflow reads `rama.json` from the checked-out target branch, so configuration changes made only in a pull request take effect after they are merged.

If the target-branch revision does not contain a `rama.json` file, or the file is empty or invalid JSON, RAMA uses its packaged default configuration and adds a configuration warning to the pull-request comment. If you want RAMA to analyze extensions or metamodels other than the defaults, add a valid `rama.json` file at the root of the repository.

The file must use this structure. The following example shows RAMA's default configuration:

```json
{
  "model_extensions": [".model"],
  "metamodels": []
}
```

- `model_extensions`: file extensions that RAMA should treat as model files.
- `metamodels`: paths of metamodel files used by RAMA when analyzing model files.

No `metamodel_extensions` option is required, as RAMA supports the two main EMF metamodel extensions: `.ecore` and `.emf`.

Use extensions including the leading dot (for example, `.model`) and repository-relative paths for `metamodels`:

```json
{
  "model_extensions": [".xmi"],
  "metamodels": ["metamodels/domain.ecore"]
}
```

Configured metamodels are loaded from the GitHub Actions workspace, which is the target branch when the supplied `pull_request_target` workflow is used. Consequently, a pull request that adds a model and the metamodel it depends on cannot yet be fully loaded by RAMA: the new metamodel is not available in that workspace. Merge the metamodel first, or expect RAMA to report a loading failure for the model. Loading metamodels directly from the pull-request revision is a planned enhancement.

## Automated system testing

RAMA uses [`reprogit`](https://github.com/alfonsodelavega/reprogit) to generate a deterministic Git repository for automated system tests. `reprogit` builds a repository history from ordered fixture folders, allowing tests to exercise commits, branches, pull requests, and history-dependent workflows.

`tests/system/fixture/` contains this test fixture. Its files and commit history are test data: preserve both unless a test intentionally requires a change to the generated repository history. See the `reprogit` repository (https://github.com/alfonsodelavega/reprogit) for fixture format and usage details.

## Acknowledgements

RAMA's development was started as the undergraduate project of [Samuel Díaz-Aja]() at the University of Cantabria. You can access the original project [here]().

RAMA has been demonstrated at the MODELS'26 tools and demonstrations track. The associated paper and citation details are as follows:

```
@inproceedings{10.1145/3837062.3838869,
author = {D{\'i}az-Aja, Samuel and S{\'a}nchez, Pablo and de la Vega, Alfonso},
title = {Facilitating model reviews in pull/change request processes with RAMA},
year = {2026},
isbn = {9798400729034},
url = {https://doi.org/10.1145/3837062.3838869},
doi = {10.1145/3837062.3838869},
booktitle = {Proceedings of the ACM/IEEE 29th International Conference on Model Driven Engineering Languages and Systems},
pages = {204–208},
numpages = {5},
keywords = {Model evolution, Model comparison, Code review, Pull request, Continuous integration.},
series = {MODELS Companion '26}
}
```